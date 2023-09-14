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
     * @param id utilisateur id
     * @return ResponseEntity
     */


    //get user by id
    @Operation(
            summary = "Récupérer un utilisateur par son id",
            description = "Récupérer un utilisateur par son id",
            tags = {"Utilisateurs"},
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
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @Operation(
            summary = "Mettre à jour un utilisateur",
            description = "Mettre à jour un utilisateur",
            tags = {"Utilisateurs"},
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
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    //update photo

    @Operation(
            summary = "Mettre à jour la photo d'un utilisateur",
            description = "Mettre à jour la photo d'un utilisateur",
            tags = {"Utilisateurs"},
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
    public ResponseEntity<?> updatePhoto(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        return userService.updatePhoto(id, file);
    }


    //update user to standard

    @Operation(
            summary = "Mettre à jour un utilisateur en standard",
            description = "Mettre à jour un utilisateur en standard",
            tags = {"Utilisateurs"},
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
            tags = {"Utilisateurs"},
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
            tags = {"Utilisateurs"},
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
            tags = {"Utilisateurs"},
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
            tags = {"Utilisateurs"},
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
            tags = {"Utilisateurs"},
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
            tags = {"Commentaires"},
            method = "GET",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Commentaires trouvés"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Commentaires non trouvés")
            }

    )
    @GetMapping("/{id}/comments")
    public List<Comments> getAllCommentsByUserId(@PathVariable int id) {
        return commentService.getAllCommentsByUserId(id);
    }


    //add comment

    @Operation(
            summary = "Ajouter un commentaire",
            description = "Ajouter un commentaire",
            tags = {"Commentaires"},
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
    public ResponseEntity<?> addComment(@RequestBody Comments comments) {
        return commentService.addComment(comments);
    }


    //update comment


    @Operation(
            summary = "Mettre à jour un commentaire",
            description = "Mettre à jour un commentaire",
            tags = {"Commentaires"},
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
    public ResponseEntity<?> updateComment(@RequestBody Comments comments) {
        return commentService.updateComment(comments);
    }


    //delete comment

    @Operation(
            summary = "Supprimer un commentaire",
            description = "Supprimer un commentaire",
            tags = {"Commentaires"},
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
    public ResponseEntity<?> deleteComment(@PathVariable int id) {
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
            tags = {"Favoris"},
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
    public ResponseEntity<?> addtoFavoris(@PathVariable int id, @RequestBody @Valid Favoris favoris) {
        return favorisService.addFavoris(favoris);
    }


    //update favoris

    @Operation(
            summary = "Mettre à jour un favoris",
            description = "Mettre à jour un favoris",
            tags = {"Favoris"},
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
            tags = {"Favoris"},
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


    //get by id
    @Operation(
            summary = "Récupérer un favoris par son id",
            description = "Récupérer un favoris par son id",
            tags = {"Favoris"},
            method = "GET",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Favoris trouvé"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Favoris non trouvé")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id du favoris", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Favoris à récupérer", required = true)

    )
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


    //get all favoris by user id

    @Operation(
            summary = "Récupérer tous les favoris d'un utilisateur",
            description = "Récupérer tous les favoris d'un utilisateur",
            tags = {"Favoris"},
            method = "GET",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Favoris trouvés"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Favoris non trouvés")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id du favoris", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Favoris à récupérer", required = true)

    )
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

    @Operation(
            summary = "Récupérer une maison par son id",
            description = "Récupérer une maison par son id",
            tags = {"Maison"},
            method = "GET",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Maison trouvée"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Maison non trouvée")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id de la maison", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Maison à récupérer", required = true)

    )
    @GetMapping("/house/{id}")
    public ResponseEntity<?> getHouseByIdAndGetPhotoByHouseId(@PathVariable int id) {
        ResponseEntity<?> house = houseService.getHouseById(1);

        List<Photo> photos = photoService.getAllPhotosByHouseId(1);

        Map<String, Object> response = new HashMap<>();
        response.put("house", house);
        response.put("photos", photos);

        return ResponseEntity.ok(response);

    }


    /*
    ##### ------------- END OF HOUSE AND PHOTO SECTION ------------------ #####
     */


    /*
    ##### ------------- START OF RESERVATION SECTION ------------------ #####

     */
    //reser by id

    @Operation(
            summary = "Récupérer une réservation par son id",
            description = "Récupérer une réservation par son id",
            tags = {"Réservation"},
            method = "GET",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Réservation trouvée"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Réservation non trouvée")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id de la réservation", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Réservation à récupérer", required = true)

    )
    @GetMapping("/reservation/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable int id) {
        return reservationService.getReservationById(id);
    }

    @Operation(
            summary = "Récupérer toutes les réservations d'un utilisateur",
            description = "Récupérer toutes les réservations d'un utilisateur",
            tags = {"Réservation"},
            method = "GET",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Réservations trouvées"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Réservations non trouvées")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id de la réservation", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Réservation à récupérer", required = true)

    )
    @GetMapping("/reservation/user/{userId}")
    @PreAuthorize("hasRole('ROLE_STANDARD_PROPRIO') or hasRole('ROLE_PREMIUM_PROPRIO') or hasRole('ROLE_STANDARD_USER') or hasRole('ROLE_PREMIUM_USER')")
    public ResponseEntity<?> getAllReservationsByUserId(@PathVariable int userId) {
        return reservationService.getReservationByUserId(userId);
    }


    @Operation(
            summary = "Récupérer toutes les réservations d'une maison",
            description = "Récupérer toutes les réservations d'une maison",
            tags = {"Réservation"},
            method = "GET",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Réservations trouvées"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Réservations non trouvées")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id de la réservation", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Réservation à récupérer", required = true)

    )
    @GetMapping("/reservation/house/{houseId}")
    public ResponseEntity<?> getAllReservationsByHouseId(@PathVariable int houseId) {
        return reservationService.getReservationByHouseId(houseId);
    }


    @Operation(
            summary = "Ajouter une réservation",
            description = "Ajouter une réservation",
            tags = {"Réservation"},
            method = "POST",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Réservation ajoutée"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Réservation non ajoutée")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id de la réservation", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Réservation à ajouter", required = true)

    )
    @PostMapping("/reservation")
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }


    @Operation(
            summary = "Mettre à jour une réservation",
            description = "Mettre à jour une réservation",
            tags = {"Réservation"},
            method = "PUT",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Réservation mise à jour"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Réservation non mise à jour")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id de la réservation", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Réservation à mettre à jour", required = true)

    )
    @PutMapping("/reservation")
    public ResponseEntity<?> updateReservation(@RequestBody Reservation reservation) {
        return reservationService.updateReservation(reservation);
    }

    @Operation(
            summary = "Supprimer une réservation",
            description = "Supprimer une réservation",
            tags = {"Réservation"},
            method = "DELETE",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Réservation supprimée"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Réservation non supprimée")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id de la réservation", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Réservation à supprimer", required = true)

    )
    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable int id) {
        return reservationService.deleteReservation(id);
    }

    /*
    ##### ------------- END OF RESERVATION SECTION ------------------ #####
     */


    /*
    ##### ------------- START OF MESSAGE SECTION ------------------ #####
     */


    @Operation(
            summary = "Récupérer un message par son id",
            description = "Récupérer un message par son id",
            tags = {"Message"},
            method = "GET",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Message trouvé"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Message non trouvé")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id du message", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Message à récupérer", required = true)

    )
    @GetMapping("/message/{id}")
    @PreAuthorize("hasRole('ROLE_STANDARD_PROPRIO') or hasRole('ROLE_PREMIUM_PROPRIO') or hasRole('ROLE_STANDARD_USER') or hasRole('ROLE_PREMIUM_USER')")
    public ResponseEntity<?> getMessageById(@PathVariable int id) {
        return messageService.getMessageById(id);
    }

    @Operation(
            summary = "Récupérer tous les messages d'un utilisateur",
            description = "Récupérer tous les messages d'un utilisateur",
            tags = {"Message"},
            method = "GET",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Messages trouvés"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Messages non trouvés")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id du message", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Message à récupérer", required = true)

    )
    @GetMapping("/message/user-receiver/{userId}")
    public ResponseEntity<?> getAllMessagesByUserId(@PathVariable int userId) {
        return messageService.getMessageByReceiver(userId);
    }

    @Operation(
            summary = "Récupérer tous les messages envoyés par un utilisateur",
            description = "Récupérer tous les messages envoyés par un utilisateur",
            tags = {"Message"},
            method = "GET",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Messages trouvés"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Messages non trouvés")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id du message", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Message à récupérer", required = true)

    )
    @GetMapping("/message/user-sender/{userId}")
    public ResponseEntity<?> getAllMessagesBySenderId(@PathVariable int userId) {
        return messageService.getMessageBySender(userId);
    }


    @Operation(
            summary = "Ajouter un message",
            description = "Ajouter un message",
            tags = {"Message"},
            method = "POST",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Message ajouté"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Message non ajouté")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id du message", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Message à ajouter", required = true)

    )
    @PostMapping("/message")
    public ResponseEntity<?> sendMessage(@RequestBody Message message) {
        return messageService.sendMessage(message);
    }


    @Operation(
            summary = "Mettre à jour un message",
            description = "Mettre à jour un message",
            tags = {"Message"},
            method = "PUT",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Message mis à jour"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Message non mis à jour")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id du message", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Message à mettre à jour", required = true)

    )
    @PutMapping("/message")
    public ResponseEntity<?> receiveMessage(@RequestBody Message message) {
        return messageService.receiveMessage(message);
    }


    @Operation(
            summary = "Supprimer un message",
            description = "Supprimer un message",
            tags = {"Message"},
            method = "DELETE",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Message supprimé"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Message non supprimé")
            },
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "Id du message", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Message à supprimer", required = true)

    )
    @DeleteMapping("/message/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable int id) {
        return messageService.deleteMessage(id);
    }

    /*
    ##### ------------- END OF MESSAGE SECTION ------------------ #####
     */


}
