package br.com.workshopMongo.config;

import br.com.workshopMongo.domain.Post;
import br.com.workshopMongo.domain.User;
import br.com.workshopMongo.dto.AuthorDTO;
import br.com.workshopMongo.dto.CommentDTO;
import br.com.workshopMongo.repositories.PostRepository;
import br.com.workshopMongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Intantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf =  new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User u1 = new User(null, "Maria Brown", "maria@gmail.com");
        User u2 = new User(null, "Alex Green", "alex@gmail.com");
        User u3 = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(u1, u2, u3));

        Post p1 = new Post(null, sdf.parse("21/03/2023"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(u1));
        Post p2 = new Post(null, sdf.parse("23/03/2023"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(u1));

        CommentDTO c1 = new CommentDTO("Boa viagem!", sdf.parse("21/03/2023"), new AuthorDTO(u2));
        CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("22/03/2023"), new AuthorDTO(u3));
        CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2023"), new AuthorDTO(u2));

        p1.getComments().addAll(Arrays.asList(c1, c2));
        p2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(p1, p2));

        u1.getPosts().addAll(Arrays.asList(p1, p2));
        userRepository.save(u1);
    }

}
