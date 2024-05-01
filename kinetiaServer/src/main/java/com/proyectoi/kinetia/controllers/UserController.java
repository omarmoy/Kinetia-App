package com.proyectoi.kinetia.controllers;

import com.proyectoi.kinetia.models.ActivityModel;
import com.proyectoi.kinetia.models.AdvertisementModel;
import com.proyectoi.kinetia.models.MessageModel;
import com.proyectoi.kinetia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@DeleteMapping(path = "deleteUser/{id}")
	public Boolean deleteUser(@PathVariable Long id) {
		return userService.deleteUser(id);
	}

	/*CRUD ACTIVITY*/

	@PostMapping(path = "/addActivity")
	public Long addActivity(@RequestBody ActivityModel activity) {
		System.out.println(activity);
		return this.userService.createActivity(activity);
	}

	@DeleteMapping(path = "/deleteActivity/{id}")
	public Boolean deleteActivity(@PathVariable("id") Long id) {
		return userService.deleteActivity(id);
	}

	@PutMapping(path = "/updateActivity")
	public Boolean updateActivity(@RequestBody ActivityModel activity) {
		return userService.updateActivity(activity);
	}


	/*FAV*/

	@PostMapping(path = "fav/{idUser}/{idActivity}")
	public Boolean favActivity(@PathVariable("idUser") Long idUser, @PathVariable("idActivity") Long idActivity) {
		return userService.addFav(idUser, idActivity);
	}
	
	@DeleteMapping(path = "noFav/{idUser}/{idActivity}")
	public Boolean noFavActivity(@PathVariable("idUser") Long idUser, @PathVariable("idActivity") Long idActivity) {
		return userService.deleteFav(idUser, idActivity);
	}


	/*RESERVAS*/

	@PostMapping(path = "reserveActivity/{idUser}/{idActivity}")
	public Boolean reserveActivity(@PathVariable("idUser") Long idUser, @PathVariable("idActivity") Long idActivity) {
		//TODO: avisar al otro usuario
		return userService.addReservation(idUser, idActivity);
	}

	@DeleteMapping(path = "cancelReservation/{idUser}/{idActivity}")
	public Boolean cancelReservation(@PathVariable("idUser") Long idUser, @PathVariable("idActivity") Long idActivity) {
		//TODO: avisar al otro usuario, dependiéndo de quién cancele, ofertante o consumidor
		return userService.cancelReservation(idUser, idActivity);
	}


	/*CRUD DE ANUNCIOS*/

	@PostMapping(path = "/addAdvertisement")
	public Long addAdvertisement(@RequestBody AdvertisementModel advertisement) {
		return userService.createAdvertisement(advertisement);
	}

	@DeleteMapping(path = "/deleteAdvertisement/{id}")
	public Boolean deleteAdvertisement(@PathVariable("id") Long id) {
		return userService.deleteAdvertisement(id);
	}

	@PutMapping(path = "/updateAdvertisement")
	public Boolean updateAdvertisement(@RequestBody AdvertisementModel advertisement) {
		return userService.updateAdvertisement(advertisement);
	}


	/*CRUD DE MENSAJES*/

	@PostMapping(path = "/sendMessage")
	public Boolean sendMessage(@RequestBody MessageModel message) {
		return userService.saveMessage(message);
	}

	@DeleteMapping(path = "/deleteChat/{idUser}/{idContact}")
	public Boolean deleteChat(@PathVariable("idUser") Long idUser, @PathVariable("idContact") Long idContact) {
		return userService.deleteChat(idUser, idContact);
	}

	@PutMapping(path = "/readMessage/{id}")
	public Boolean readMessage(@PathVariable("id") Long idMessage) {
		return userService.updateMessage(idMessage);
	}


}
