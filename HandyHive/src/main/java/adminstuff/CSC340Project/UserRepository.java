package adminstuff.CSC340Project;
import org.springframework.data.jpa.repository.JpaRepository;

//This uses Spring Data JPA to create a simple user repository
// so that we can access the user data in the future
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
