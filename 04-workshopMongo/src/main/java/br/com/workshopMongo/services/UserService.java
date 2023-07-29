package br.com.workshopMongo.services;

import br.com.workshopMongo.domain.User;
import br.com.workshopMongo.dto.UserDTO;
import br.com.workshopMongo.repositories.UserRepository;
import br.com.workshopMongo.services.exceptions.DatabaseException;
import br.com.workshopMongo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Could not find object."));
    }

    public User insert(User obj) {
        return repository.insert(obj);
    }

    public void delete(String id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
            } else {
                throw new ObjectNotFoundException("Could not find object.");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(String id, User obj) {
        User entity = findById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
    }

    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }

}
