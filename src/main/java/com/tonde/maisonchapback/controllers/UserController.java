package com.tonde.maisonchapback.controllers;


import com.tonde.maisonchapback.models.workflows.*;
import com.tonde.maisonchapback.models.workflows.user.User;
import com.tonde.maisonchapback.services.implementation.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
@PreAuthorize("hasRole('ROLE_FREE_USER') or hasRole('ROLE_STANDARD_USER') or hasRole('ROLE_PREMIUM_USER')")
public class UserController {

    /*
     ### SERVICES INJECTIONS
     */
    public final UserServiceImpl userService;
    private final CommentServiceImpl commentService;
    private final SearchServiceImpl searchService;
    private final FavorisServiceImpl favorisService;
    private final HouseServiceImpl houseService;
    private final ReservationServiceImpl reservationService;
    private final MessageServiceImpl messageService;
    private final PhotoServiceImpl photoService;
    /*
     ### END OF INJECTIONS
     */


    /*
     * ##"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""##
     *  User properties parts  Start
     * ##______________________________________________________________________________________##
     */


    /**
     *
     * @param id utilisateur id
     * @return ResponseEntity
     */


    //get user by id

    @Operation(
            summary = "Récupérer un utilisateur par son id",
            description = "Récupérer un utilisateur par son id",
            tags = { "Utilisateurs" },
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Utilisateur trouvé"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id de l'utilisateur", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Utilisateur à créer", required = true)

    )

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @Operation(
            summary = "Mettre à jour un utilisateur",
            description = "Mettre à jour un utilisateur",
            tags = { "Utilisateurs" },
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Utilisateur mis à jour"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id de l'utilisateur", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Utilisateur à mettre à jour", required = true)

    )
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    //update photo

    @Operation(
            summary = "Mettre à jour la photo d'un utilisateur",
            description = "Mettre à jour la photo d'un utilisateur",
            tags = { "Utilisateurs" },
            method = "PUT",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Photo mise à jour"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id de l'utilisateur", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Photo à mettre à jour", required = true
            )

    )
    @PutMapping("/{id}/photo")
    public ResponseEntity<?> updatePhoto(@PathVariable int id, @RequestParam("file") MultipartFile file){
        return userService.updatePhoto(id, file);
    }


    //update user to standard

    @Operation(
            summary = "Mettre à jour un utilisateur en standard",
            description = "Mettre à jour un utilisateur en standard",
            tags = { "Utilisateurs" },
            method = "PUT",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Utilisateur mis à jour"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id de l'utilisateur", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Utilisateur à mettre à jour", required = true)

    )
    @PutMapping("/{id}/upgrade-user/standard")
    public ResponseEntity<?> upgradeUserToStandard(@PathVariable int id, @RequestBody Abonnement abonnement) {
        return userService.upgradeUserToStandard(id, abonnement);
    }


    //update user to free

    @Operation(
            summary = "Mettre à jour un utilisateur en gratuit",
            description = "Mettre à jour un utilisateur en gratuit",
            tags = { "Utilisateurs" },
            method = "PUT",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Utilisateur mis à jour"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id de l'utilisateur", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Utilisateur à mettre à jour", required = true)

    )
    @PutMapping("/{id}/upgrade-user/free")
    public ResponseEntity<?> upgradeUserToFree(@PathVariable int id, @RequestBody Abonnement abonnement) {
        return userService.upgradeUserToFree(id, abonnement);
    }



    //update user to premium

    @Operation(
            summary = "Mettre à jour un utilisateur en premium",
            description = "Mettre à jour un utilisateur en premium",
            tags = { "Utilisateurs" },
            method = "PUT",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Utilisateur mis à jour"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id de l'utilisateur", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Utilisateur à mettre à jour", required = true)

    )
    @PutMapping("/{id}/upgrade-user/premium")
    public ResponseEntity<?> upgradeUserToPremium(@PathVariable int id, @RequestBody Abonnement abonnement) {
        return userService.upgradeUserToPremium(id, abonnement);
    }

    @Operation(
            summary = "Mettre à jour un propriétaire en standard",
            description = "Mettre à jour un propriétaire en standard",
            tags = { "Utilisateurs" },
            method = "PUT",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Utilisateur mis à jour"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id de l'utilisateur", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Utilisateur à mettre à jour", required = true)

    )
    @PutMapping("/{id}/upgrade-proprio/standard")
    public ResponseEntity<?> upgradeProprioToStandard(@PathVariable int id, @RequestBody Abonnement abonnement) {
        return userService.updateProprioToStandard(id, abonnement);

    }


    @Operation(
            summary = "Mettre à jour un propriétaire en premium",
            description = "Mettre à jour un propriétaire en premium",
            tags = { "Utilisateurs" },
            method = "PUT",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Utilisateur mis à jour"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id de l'utilisateur", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Utilisateur à mettre à jour", required = true)

    )

    @PutMapping("/{id}/upgrade-proprio/premium")
    public ResponseEntity<?> upgradeProprioToPremium(@PathVariable int id, @RequestBody Abonnement abonnement) {
        return userService.updateProprioToPremium(id, abonnement);
    }


    @Operation(
            summary = "Mettre à jour un propriétaire en gratuit",
            description = "Mettre à jour un propriétaire en gratuit",
            tags = { "Utilisateurs" },
            method = "PUT",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Utilisateur mis à jour"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Utilisateur non trouvé")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id de l'utilisateur", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Utilisateur à mettre à jour", required = true)

    )

    @PutMapping("/{id}/upgrade-proprio/free")
    public ResponseEntity<?> updateProprioToStandard(@PathVariable int id, @RequestBody Abonnement abonnement) {
        return userService.updateProprioToFree(id, abonnement);
    }

    /*
     * ##"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""##
     *  User properties parts  Start
     * ##______________________________________________________________________________________##
     */

    /*
     * ##"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""""##
     *  CommentPart
     * ##______________________________________________________________________________________##
     */


    //get all comments

    @Operation(
            summary = "Récupérer tous les commentaires",
            description = "Récupérer tous les commentaires",
            tags = { "Commentaires" },
            method = "GET",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Commentaires trouvés"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Commentaires non trouvés")
            }

    )
    @GetMapping("/{id}/comments")
    public List<Comments> getAllCommentsByUserId(@PathVariable int id){
        return  commentService.getAllCommentsByUserId(id);
    }


    //add comment

    @Operation(
            summary = "Ajouter un commentaire",
            description = "Ajouter un commentaire",
            tags = { "Commentaires" },
            method = "POST",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Commentaire ajouté"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Commentaire non ajouté")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id du commentaire", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Commentaire à ajouter", required = true)

    )
    @PostMapping("/{id}/comments")
    public ResponseEntity<?> addComment(@RequestBody Comments comments){
        return commentService.addComment(comments);
    }


    //update comment


    @Operation(
            summary = "Mettre à jour un commentaire",
            description = "Mettre à jour un commentaire",
            tags = { "Commentaires" },
            method = "PUT",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Commentaire mis à jour"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Commentaire non trouvé")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id du commentaire", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Commentaire à mettre à jour", required = true)

    )
    @PutMapping("/{id}/comments")
    public ResponseEntity<?> updateComment(@RequestBody Comments comments){
        return commentService.updateComment(comments);
    }


    //delete comment

    @Operation(
            summary = "Supprimer un commentaire",
            description = "Supprimer un commentaire",
            tags = { "Commentaires" },
            method = "DELETE",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Commentaire supprimé"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Commentaire non trouvé")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id du commentaire", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Commentaire à supprimer", required = true)

    )

    @DeleteMapping("/{id}/comments")
    public ResponseEntity<?> deleteComment(@PathVariable int id){
        return commentService.deleteComment(id);
    }

    /*
    #''''''''''''''''''''''''' END OF COMMENTS SECTION ''''''''''''''''''''''''''''''''''''''''''''''###
     */

    /*
    #''''''''''''''''START OF FAVORIS SECTION
     */

    //add to favoris

    @Operation(
            summary = "Ajouter un favoris",
            description = "Ajouter un favoris",
            tags = { "Favoris" },
            method = "POST",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Favoris ajouté"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Favoris non ajouté")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id du favoris", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Favoris à ajouter", required = true)

    )

    @PostMapping("/{id}/favoris")
    @PreAuthorize("hasRole('ROLE_STANDARD_PROPRIO') or hasRole('ROLE_PREMIUM_PROPRIO') or hasRole('ROLE_STANDARD_USER') or hasRole('ROLE_PREMIUM_USER')")
    public ResponseEntity<?> addtoFavoris(@PathVariable int id, @RequestBody @Valid Favoris favoris){
        return  favorisService.addFavoris(favoris);
    }


    //update favoris

    @Operation(
            summary = "Mettre à jour un favoris",
            description = "Mettre à jour un favoris",
            tags = { "Favoris" },
            method = "PUT",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Favoris mis à jour"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Favoris non trouvé")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id du favoris", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Favoris à mettre à jour", required = true)

    )
    @PutMapping("/{id}/favoris")
    @PreAuthorize("hasRole('ROLE_STANDARD_PROPRIO') or hasRole('ROLE_PREMIUM_PROPRIO') or hasRole('ROLE_STANDARD_USER') or hasRole('ROLE_PREMIUM_USER')")
    public ResponseEntity<?> updateFavoris(@RequestBody @Valid Favoris favoris, @PathVariable int id) {
        return favorisService.updateFavoris(favoris);
    }


    //delete favoris

    @Operation(
            summary = "Supprimer un favoris",
            description = "Supprimer un favoris",
            tags = { "Favoris" },
            method = "DELETE",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Favoris supprimé"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Favoris non trouvé")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id du favoris", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Favoris à supprimer", required = true)

    )
    @DeleteMapping("/favoris/delete")
    @PreAuthorize("hasRole('ROLE_STANDARD_PROPRIO') or hasRole('ROLE_PREMIUM_PROPRIO') or hasRole('ROLE_STANDARD_USER') or hasRole('ROLE_PREMIUM_USER')")
    public ResponseEntity<?> deleteFavoris(@RequestBody @Valid Favoris favoris) {
        return favorisService.deleteFavoris(favoris);
    }

    @GetMapping("/favoris/{id}")
    @PreAuthorize("hasRole('ROLE_STANDARD_PROPRIO') or hasRole('ROLE_PREMIUM_PROPRIO') or hasRole('ROLE_STANDARD_USER') or hasRole('ROLE_PREMIUM_USER')")
    public ResponseEntity<Favoris> getFavorisById(@PathVariable int id) {
        Favoris favoris = favorisService.getFavorisById(id);
        if (favoris != null) {
            return ResponseEntity.ok(favoris);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ROLE_STANDARD_PROPRIO') or hasRole('ROLE_PREMIUM_PROPRIO') or hasRole('ROLE_STANDARD_USER') or hasRole('ROLE_PREMIUM_USER')")
    public List<Favoris> getAllFavorisByUserId(@PathVariable int userId) {
        return favorisService.getAllFavorisByUserId(userId);
    }

    /*
    ##### ------------- END OF FAVORIS SECTION ------------------ #####
     */


    /*
    ##### ------------- START OF HOUSE AND PHOTO SECTION ------------------ #####
     */

    @GetMapping("/house/{id}")
    public ResponseEntity<?> getHouseByIdAndgetPhotoByHouseId(@PathVariable int id){
        ResponseEntity<?> house = houseService.getHouseById(1);

        List<Photo> photos = photoService.getAllPhotosByHouseId(1);

        Map<String, Object> response = new HashMap<>();
        response.put("house", house);
        response.put("photos", photos);

        // Return the response
        return ResponseEntity.ok(response);

    }


    /*
    ##### ------------- END OF HOUSE AND PHOTO SECTION ------------------ #####
     */


    /*
    ##### ------------- START OF RESERVATION SECTION ------------------ #####

     */

    @GetMapping("/reservation/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable int id){
        return reservationService.getReservationById(id);
    }

    @GetMapping("/reservation/user/{userId}")
    @PreAuthorize("hasRole('ROLE_STANDARD_PROPRIO') or hasRole('ROLE_PREMIUM_PROPRIO') or hasRole('ROLE_STANDARD_USER') or hasRole('ROLE_PREMIUM_USER')")
    public ResponseEntity<?> getAllReservationsByUserId(@PathVariable int userId){
        return  reservationService.getReservationByUserId(userId);
    }

    @GetMapping("/reservation/house/{houseId}")
    public ResponseEntity<?> getAllReservationsByHouseId(@PathVariable int houseId){
        return reservationService.getReservationByHouseId(houseId);
    }

    @PostMapping("/reservation")
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation){
        return reservationService.createReservation(reservation);
    }

    @PutMapping("/reservation")
    public ResponseEntity<?> updateReservation(@RequestBody Reservation reservation){
        return reservationService.updateReservation(reservation);
    }

    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable int id){
        return reservationService.deleteReservation(id);
    }

    /*
    ##### ------------- END OF RESERVATION SECTION ------------------ #####
     */


    /*
    ##### ------------- START OF MESSAGE SECTION ------------------ #####
     */

    @GetMapping("/message/{id}")
    @PreAuthorize("hasRole('ROLE_STANDARD_PROPRIO') or hasRole('ROLE_PREMIUM_PROPRIO') or hasRole('ROLE_STANDARD_USER') or hasRole('ROLE_PREMIUM_USER')")
    public ResponseEntity<?> getMessageById(@PathVariable int id){
        return messageService.getMessageById(id);
    }

    @GetMapping("/message/user-receiver/{userId}")
    public ResponseEntity<?> getAllMessagesByUserId(@PathVariable int userId){
        return messageService.getMessageByReceiver(userId);
    }

    @GetMapping("/message/user-sender/{userId}")
    public ResponseEntity<?> getAllMessagesBySenderId(@PathVariable int userId){
        return messageService.getMessageBySender(userId);
    }

    @PostMapping("/message")
    public ResponseEntity<?> sendMessage(@RequestBody Message message){
        return messageService.sendMessage(message);
    }

    @PutMapping("/message")
    public ResponseEntity<?> receiveMessage(@RequestBody Message message){
        return messageService.receiveMessage(message);
    }

    @DeleteMapping("/message/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable int id){
        return messageService.deleteMessage(id);
    }

    /*
    ##### ------------- END OF MESSAGE SECTION ------------------ #####
     */


}
