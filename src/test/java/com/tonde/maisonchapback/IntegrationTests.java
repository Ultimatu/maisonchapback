package com.tonde.maisonchapback;


import com.tonde.maisonchapback.domains.User;
import com.tonde.maisonchapback.domains.enums.Role;
import com.tonde.maisonchapback.requests.AuthenticationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class IntegrationTests {


    private final MockMvc mockMvc;


    public IntegrationTests(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }


    @Test
    void testGetAllHouses() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/public/houses/all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    void testGetAllRentHouses() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/public/houses/renting"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    void testGetAllSaleHouses() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/public/houses/selling"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    void authTest() throws Exception {
        var user = User.builder().
                nom("test")
                .prenom("test")
                .email("test@gmail.com")
                .phone("test")
                .adresse("test")
                .role(Role.PREMIUM_USER)
                .photoPath("test")
                .langkey("test")
                .password(new BCryptPasswordEncoder().encode("test"))
                .build();



        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
                .contentType("application/json")
                .content(String.valueOf(user)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.access_token").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.refresh_token").exists());
    }


    @Test
    void authenticateTest() throws Exception {
        var user = AuthenticationRequest.builder()
                .email("test@gmail.com")
                .password("test")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/authenticate")
                        .contentType("application/json")
                        .content(user.toString()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.access_token").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.refresh_token").exists());


    }


    @Test

    public void testGetAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/authorities"))
                .andExpect(status().is(403));
    }






}
