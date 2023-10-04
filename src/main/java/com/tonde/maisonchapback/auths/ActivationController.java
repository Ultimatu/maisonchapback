package com.tonde.maisonchapback.auths;


import com.tonde.maisonchapback.checker.CheckIfUserAlreadyExists;
import com.tonde.maisonchapback.config.LogoutService;
import com.tonde.maisonchapback.exceptions.CustomLogger;
import com.tonde.maisonchapback.requests.AccountActivationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/auth/activate")
@RequiredArgsConstructor
public class ActivationController {

    private static final Logger logger = LoggerFactory.getLogger(ActivationController.class);
    private static final String MESSAGE = "message";
    private final AuthenticationService authentificationService;
    private final LogoutService logoutService;
    private final CheckIfUserAlreadyExists check;
    @Value("${application.frontend.url}")
    private String frontendUrl;



    @Operation(
            summary = "Activation du compte d'un utilisateur",
            description = "Permet à un utilisateur d'activer son compte.",
            tags = {"Authentification"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Activation réussie",
                            content = @Content(schema = @Schema(implementation = Object.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Utilisateur non trouvé",
                            content = @Content(schema = @Schema(implementation = String.class))
                    )
            }
    )

    @GetMapping("")
    public String activate(@RequestParam("key") String key, @RequestParam("userId") Integer userId, HttpServletResponse response, Model model) {
        AccountActivationRequest request = new AccountActivationRequest();
        request.setKey(key);
        request.setUserId(userId);

        if (!check.alreadyExist(request.getUserId())) {
            logger.info("User not found on activation");
            model.addAttribute("loginUrl", frontendUrl+"/login");
            return "activation/failled";
        }


        if (!authentificationService.activateAccount(request)){
            CustomLogger.log("INFO", "Hello");
            model.addAttribute("loginUrl", frontendUrl+"/login");
            return "activation/failled";
        }
        else{
            model.addAttribute("loginUrl", frontendUrl+"/login");
            return "activation/activated";
        }

    }
}
