package org.zerock.apiserver.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.zerock.apiserver.domain.*;
import org.zerock.apiserver.domain.auth.Role;
import org.zerock.apiserver.domain.auth.User;
import org.zerock.apiserver.repository.board.BoardRepository;
import org.zerock.apiserver.repository.category.CategoryRepository;
import org.zerock.apiserver.repository.comment.CommentRepository;
import org.zerock.apiserver.repository.file.FileRepository;
import org.zerock.apiserver.repository.post.PostRepository;
import org.zerock.apiserver.repository.role.RoleRepository;
import org.zerock.apiserver.repository.user.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final FileRepository fileRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private Random random = new Random();

    public DataInitializer(CategoryRepository categoryRepository,
                           BoardRepository boardRepository,
                           PostRepository postRepository,
                           CommentRepository commentRepository,
                           FileRepository fileRepository,
                           RoleRepository roleRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.boardRepository = boardRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.fileRepository = fileRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0 && roleRepository.count() == 0) {
            initializeData();
        }
    }

    private void initializeData() {
        List<Role> roles = createRoles();
        List<User> users = createUsers(roles);
        List<Category> categories = createCategories();
        List<Board> boards = createBoards(categories);
        List<Post> posts = createPosts(boards, users);
        createComments(posts, users);
        createFiles(posts);
    }

    private List<Role> createRoles() {
        List<String> roleNames = Arrays.asList("ROLE_ADMIN", "ROLE_TEACHER", "ROLE_STUDENT", "ROLE_USER", "ROLE_GUEST");
        List<Role> roles = new ArrayList<>();
        for (String roleName : roleNames) {
            Role role = new Role();
            role.setName(roleName);
            roles.add(roleRepository.save(role));
        }
        return roles;
    }

    private List<User> createUsers(List<Role> roles) {
        List<User> users = new ArrayList<>();
        String[] usernames = {"admin", "teacher1", "teacher2", "student1", "student2", "user1", "user2"};
        for (String username : usernames) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode("12345"));
            user.setEnabled(true);

            Set<Role> userRoles = new HashSet<>();
            if (username.equals("admin")) {
                userRoles.add(roles.get(0)); // ROLE_ADMIN
            } else if (username.startsWith("teacher")) {
                userRoles.add(roles.get(1)); // ROLE_TEACHER
            } else if (username.startsWith("student")) {
                userRoles.add(roles.get(2)); // ROLE_STUDENT
            } else {
                userRoles.add(roles.get(3)); // ROLE_USER
            }
            user.setRoles(userRoles);

            users.add(userRepository.save(user));
        }
        return users;
    }

    private List<Category> createCategories() {
        List<Category> categories = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Category category = Category.builder()
                    .name("Category " + i)
                    .regDt(LocalDate.now())
                    .build();
            categories.add(categoryRepository.save(category));
        }
        return categories;
    }

    private List<Board> createBoards(List<Category> categories) {
        List<Board> boards = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Board board = Board.builder()
                    .name("Board " + i)
                    .description("Description for Board " + i)
                    .allowComments(random.nextBoolean())
                    .commentLevel(random.nextInt(3) + 1)
                    .allowAttachments(random.nextBoolean())
                    .isActive(true)
                    .isPrivate(random.nextBoolean())
                    .adminOnlyWrite(random.nextBoolean())
                    .allowOnlyAdminOrAuthorComments(random.nextBoolean())
                    .regDt(LocalDateTime.now())
                    .category(categories.get(random.nextInt(categories.size())))
                    .build();
            boards.add(boardRepository.save(board));
        }
        return boards;
    }

    private List<Post> createPosts(List<Board> boards, List<User> users) {
        List<Post> posts = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Post post = Post.builder()
                    .title("Post " + i)
                    .content("Content for Post " + i)
                    .userId(users.get(random.nextInt(users.size())).getId())
                    .board(boards.get(random.nextInt(boards.size())))
                    .build();
            posts.add(postRepository.save(post));
        }
        return posts;
    }

    private void createComments(List<Post> posts, List<User> users) {
        for (int i = 1; i <= 10; i++) {
            Comment comment = Comment.builder()
                    .content("Comment " + i)
                    .userId(users.get(random.nextInt(users.size())).getId())
                    .post(posts.get(random.nextInt(posts.size())))
                    .build();
            commentRepository.save(comment);
        }
    }

    private void createFiles(List<Post> posts) {
        for (int i = 1; i <= 10; i++) {
            File file = File.builder()
                    .filePath("/path/to/file" + i)
                    .fileName("file" + i + ".txt")
                    .fileSize((long) (random.nextInt(10000) + 1000))
                    .fileType("text/plain")
                    .post(posts.get(random.nextInt(posts.size())))
                    .build();
            fileRepository.save(file);
        }
    }
}