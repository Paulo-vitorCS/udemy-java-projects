package br.com.workshopMongo.services;

import br.com.workshopMongo.domain.Post;
import br.com.workshopMongo.repositories.PostRepository;
import br.com.workshopMongo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post findById(String id) {
        Optional<Post> obj = repository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Could not find object."));
    }

}
