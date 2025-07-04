package br.com.dio.dao;

import br.com.dio.dao.expection.UserNotFoundException;
import model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private  long nextId = 1L;

    private final  List<UserModel> models = new ArrayList<>();

    public UserModel save(final UserModel model) {
      //Contador de usuarios atraves do nextId
        model.setId(nextId ++);
      models.add(model);
      return model;

    }
    public UserModel update(final UserModel model){
      var toUpdate = findById(model.getId());
      models.remove(toUpdate);
      models.add(model);
      return model;
    }
    public void delete(final long id ){
        var toDelete = findById(id);
        models.remove(toDelete);
    }

    public UserModel findById(final long id ){
       var  message = String.format("Não existe o usuario com o id %s cadastrado",id);
     return models.stream()
                .filter(u ->u.getId() == id)
                .findFirst()
                .orElseThrow(()-> new UserNotFoundException(message));
    }
    public List<UserModel> findAll(){
        return models;
    }

}
