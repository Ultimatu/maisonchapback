package com.tonde.maisonchapback.controllers;


import com.tonde.maisonchapback.models.workflows.*;
import com.tonde.maisonchapback.models.workflows.user.User;
import com.tonde.maisonchapback.services.implementation.*;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @PutMapping("/{id}/photo")
    public ResponseEntity<?> updatePhoto(@PathVariable int id, @RequestParam("file") MultipartFile file){
        return userService.updatePhoto(id, file);
    }

    @PostMapping("/{id}/upgrade-user/standard")
    public ResponseEntity<?> upgradeUserToStandard(@PathVariable int id, @RequestBody Abonnement abonnement) {
        return userService.upgradeUserToStandard(id, abonnement);
    }

    @PostMapping("/{id}/upgrade-user/free")
    public ResponseEntity<?> upgradeUserToFree(@PathVariable int id, @RequestBody Abonnement abonnement) {
        return userService.upgradeUserToFree(id, abonnement);
    }


    @PostMapping("/{id}/upgrade-user/premium")
    public ResponseEntity<?> upgradeUserToPremium(@PathVariable int id, @RequestBody Abonnement abonnement) {
        return userService.upgradeUserToPremium(id, abonnement);
    }


    @PostMapping("/{id}/upgrade-proprio/standard")
    public ResponseEntity<?> upgradeProprioToStandard(@PathVariable int id, @RequestBody Abonnement abonnement) {
        return userService.updateProprioToStandard(id, abonnement);

    }

    @PostMapping("/{id}/upgrade-proprio/premium")
    public ResponseEntity<?> upgradeProprioToPremium(@PathVariable int id, @RequestBody Abonnement abonnement) {
        return userService.updateProprioToPremium(id, abonnement);
    }

    @PostMapping("/{id}/upgrade-proprio/free")
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

    @GetMapping("/{id}/comments")
    public List<Comments> getAllCommentsByUserId(@PathVariable int id){
        return  commentService.getAllCommentsByUserId(id);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<?> addComment(@RequestBody Comments comments){
        return commentService.addComment(comments);
    }

    @PutMapping("/{id}/comments")
    public ResponseEntity<?> updateComment(@RequestBody Comments comments){
        return commentService.updateComment(comments);
    }

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

    @PostMapping("/{id}/favoris")
    @PreAuthorize("hasRole('ROLE_STANDARD_PROPRIO') or hasRole('ROLE_PREMIUM_PROPRIO') or hasRole('ROLE_STANDARD_USER') or hasRole('ROLE_PREMIUM_USER')")
    public ResponseEntity<?> addtoFavoris(@PathVariable int id, @RequestBody @Valid Favoris favoris){
        return  favorisService.addFavoris(favoris);
    }


    @PutMapping("/{id}/favoris")
    @PreAuthorize("hasRole('ROLE_STANDARD_PROPRIO') or hasRole('ROLE_PREMIUM_PROPRIO') or hasRole('ROLE_STANDARD_USER') or hasRole('ROLE_PREMIUM_USER')")
    public ResponseEntity<?> updateFavoris(@RequestBody @Valid Favoris favoris, @PathVariable int id) {
        return favorisService.updateFavoris(favoris);
    }

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
