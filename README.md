ReadMe για την Εφαρμογή Αντιπροσωπείας Αυτοκινήτων

Περιγραφή Έργου

Η παρούσα εφαρμογή αποτελεί μια αρχική υλοποίηση ενός συστήματος αντιπροσωπείας αυτοκινήτων με χρήση Spring Boot και κληρονομικότητα στις κλάσεις User, Citizen και Dealership. Περιλαμβάνει μόνο μια βασική λειτουργία: το endpoint /test για επιβεβαίωση της λειτουργίας της εφαρμογής.

Τεχνολογίες
	•	Java 17
	•	Spring Boot 3.4.0
	•	Spring Data JPA
	•	MySQL
	•	Hibernate

Δομή Βάσης Δεδομένων

Η βάση δεδομένων χρησιμοποιεί τη στρατηγική Single Table Inheritance για την κλάση User, με τις Citizen και Dealership να είναι υποκλάσεις.

Σχήμα   Πίνακα    User:

Όνομα  Στήλης	Τύπος Δεδομένων	Περιγραφή
id	        BIGINT	       Πρωτεύον Κλειδί
username        VARCHAR	       Όνομα χρήστη
password        VARCHAR	       Κρυπτογραφημένος κωδικός
email	        VARCHAR	       Email χρήστη
dtype	        VARCHAR	       Διακριτής τύπος για τις υποκλάσεις
location	VARCHAR	       Τοποθεσία (ειδικό για Dealership)
contact_info	VARCHAR	       Πληροφορίες επικοινωνίας
name	        VARCHAR	        Όνομα αντιπροσωπείας

Δομή Κλάσεων
1.	User (Βασική Κλάση)

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    // Getters και Setters
}

2.	Citizen (Υποκλάση)

@Entity
@DiscriminatorValue("Citizen")
public class Citizen extends User {
    // Πρόσθετα πεδία για τον Citizen
}

3.	Dealership (Υποκλάση)

@Entity
@DiscriminatorValue("Dealership")
public class Dealership extends User {
    private String location;
    private String contactInfo;
    private String name;

    // Getters και Setters
}

Λειτουργικότητα

Endpoint: /test
	•	HTTP Μέθοδος: GET
	•	Περιγραφή: Ένα απλό endpoint για να επιβεβαιωθεί ότι η εφαρμογή λειτουργεί σωστά.
	•	Απάντηση: "Hello, World!"

Controller:

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public String test() {
        return "Hello, World!";
    }
}

Βήματα Εκτέλεσης
1.	Κλωνοποίηση του Αποθετηρίου:

git clone <repository-url>
cd car-dealership


2.	Ρύθμιση της Βάσης Δεδομένων:
	•	Δημιουργήστε μια βάση δεδομένων MySQL με όνομα car_dealership.
	•	Προσθέστε τα παρακάτω στο αρχείο application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/car_dealership
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update


3.	Εκτέλεση της Εφαρμογής:

mvn spring-boot:run


4.	Δοκιμή της Εφαρμογής:
	•	Ανοίξτε τον browser ή το Postman και κάντε GET request στο:

http://localhost:8080/users/test

Μελλοντικές Βελτιώσεις
	•	Επέκταση λειτουργικότητας, όπως:
	•	Εγγραφή Citizen και Dealership.
	•	Προσθήκη αυτοκινήτων.
	•	Αναζήτηση αυτοκινήτων.
	•	Κράτηση test drives.

Το ReadMe αυτό εξηγεί την τρέχουσα κατάσταση της εφαρμογής και χρησιμεύει ως βάση για μελλοντικές επεκτάσεις.
