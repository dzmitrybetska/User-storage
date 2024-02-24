<br />
<div align="center">
<h3 align="center">User's storage</h3>

  <p align="center">
   Dmitriy Betska’s project!

  </p>
</div>

## About The Project

This project is created to add and store users in a database. It consists of 2 parts:
- backend
- frontend

The application implements:

Server part:
- [x] Extract user data from a json file and add it to the database
- [x] Getting all users from the database
- [x] Pagination
- [x] Exception handler for validation exceptions
- [x] Written tests for the service layer
- [x] Data validation
- [x] Generating a json file with 50,000 generated users to transfer the client part.

Client part:
- [x] Display users
- [x] Sorting users by several parameters
- [x] Search for users
- [x] Pagination
- [x] Handling errors received from the server side
- [x] Adding and sending a json file with users to the server

## Technologies

The project was developed using:

Server part:
* Spring Boot
    * Data JPA
    * Validation
    * Test
    * Jackson
* MySQL 8
* Java 17+
* Faker
* Log4j2 as a logging system
* Mapstruct
* Lombok

Client part:
* React.js
* Bootstrap

## Demonstration of some project possibilities

Implemented exception tracking and handling layer

    @Slf4j
    @RestControllerAdvice
    public class ExceptionHandlerController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintValidationException(ConstraintViolationException exception) {
        log.warn(EXCEPTION, exception.getMessage());
        return ResponseEntity.badRequest().body(buildErrorResponse(exception));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception) {
        log.warn(EXCEPTION, exception.getMessage());
        return ResponseEntity.badRequest().body(buildErrorResponse(exception));
    }

    @ExceptionHandler(JsonConversionException.class)
    public ResponseEntity<ErrorResponse> handleJsonMappingException(JsonConversionException exception) {
        log.warn(EXCEPTION, exception.getMessage());
        return ResponseEntity.badRequest().body(buildErrorResponse(exception));
    }

    private ErrorResponse buildErrorResponse(ConstraintViolationException exception) {
        return ErrorResponse.builder()
                .errorCount(exception.getConstraintViolations().size())
                .time(LocalDateTime.now())
                .message(VALIDATION_MESSAGE)
                .build();
    }

    private ErrorResponse buildErrorResponse(SQLIntegrityConstraintViolationException exception) {
        return ErrorResponse.builder()
                .time(LocalDateTime.now())
                .message(format(SQL_VALIDATION_MESSAGE, exception.getMessage()))
                .build();
    }

    private ErrorResponse buildErrorResponse(JsonConversionException exception) {
        return ErrorResponse.builder()
                .time(LocalDateTime.now())
                .message(exception.getMessage())
                .build();
    }
    }

Implemented testing of Services layer methods

    @SpringBootTest
    @ExtendWith(MockitoExtension.class)
    @DisplayName("Testing methods of the UserService")
    public class UserServiceTest {

    private UserService userService;
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private UserMapper userMapper;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        userService = new UserServiceImpl(userMapper, encryptionService, userRepository);
    }

    @ParameterizedTest
    @ArgumentsSource(UsersAddArguments.class)
    void addUsers(List<UserRequest> userRequests, List<User> users, List<UserResponse> userResponses) {
        when(userRepository.saveAll(anyList()))
                .thenReturn(users);
        List<UserResponse> actualUserResponses = userService.addUsers(userRequests);
        assertEquals(userResponses, actualUserResponses);
    }

    @ParameterizedTest
    @ArgumentsSource(UsersGetArguments.class)
    void readAll(List<User> users, List<UserResponse> userResponses, Pageable pageable) {
        List<UserResponse> responsesModified = userResponses.stream()
                .map(userResponse -> userResponse.setSurname(
                        userResponse.getSurname() + encryptionService.encrypt(userResponse.getName()))
                )
                .toList();
        Page<UserResponse> page = new PageImpl<>(responsesModified, pageable, 3);
        when(userRepository.findAll(pageable))
                .thenReturn(new PageImpl<>(users, pageable, 3));
        Page<UserResponse> actualPage = userService.readAll(pageable);
        assertEquals(page, actualPage);
    }
    }

A function that is used to handle asynchronous requests or operations that may cause data loading or error handling. 
It returns several states and a fetching function that can be called to perform an asynchronous request.
       
    export const useFetching = (callback) => {
    const [isLoading, setIsLoading] = useState(false);
    const [errorResponse, setErrorResponse] = useState();
    const [errorMessage, setErrorMessage] = useState('');
    const [isSuccess, setIsSuccess] = useState(false);

    const fetching = async (...args) => {
        try {
            setIsLoading(true);
            await callback(...args);
            setIsSuccess(true);
            setErrorMessage('');
            setErrorResponse(null);
        } catch (error) {
            if (error.response && error.response.status === 400) {
                const validationError = error.response.data;
                setErrorResponse(validationError);
                setErrorMessage(error.message);
            } else {
                setErrorMessage(error.message);
            }
            setIsSuccess(false);
        } finally {
            setIsLoading(false);
        }
    };

    return [fetching, isLoading, errorMessage, isSuccess, errorResponse];
    }

The useSortedUsers and useUsers functions are used to filter and sort the list of users based on specified parameters.

    export const useSortedUsers = (users, sort) => {
    return useMemo(() => {
    if (sort) {
    return [...users].sort((a, b) => a[sort].localeCompare(b[sort]));
    }
    return users;
    }, [users, sort]
    );
    }

    export const useUsers = (users, sort, query) => {
    const sortedUsers = useSortedUsers(users, sort);
    const queryInLowerCase = query.toLowerCase();
    return useMemo(() => {
    return sortedUsers.filter((user) => user.name.toLowerCase().includes(queryInLowerCase)
    || user.surname.toLowerCase().includes(queryInLowerCase)
    || user.login.toLowerCase().includes(queryInLowerCase))
    }, [sortedUsers, queryInLowerCase]);
    }


## Contact

Dmitriy Betska - d.betska@gmail.com
                 +48 572 486 855

Project Link: [https://github.com/dzmitrybetska/User-storage](https://github.com/dzmitrybetska/User-storage)