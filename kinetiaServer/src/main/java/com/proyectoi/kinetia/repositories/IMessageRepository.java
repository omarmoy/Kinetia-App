package com.proyectoi.kinetia.repositories;

import com.proyectoi.kinetia.models.MessageModel;
import com.proyectoi.kinetia.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface IMessageRepository extends JpaRepository<MessageModel, Long>{
    ArrayList<MessageModel> findBySenderAndReceiver(UserModel sender, UserModel receiver);
}
