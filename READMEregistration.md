ReadMe: Εγγραφή Χρηστών (Citizen & Dealership)

Περιγραφή

Αυτή η λειτουργία υλοποιεί την εγγραφή χρηστών με χρήση κληρονομικότητας. Υπάρχουν δύο τύποι χρηστών: Citizen και Dealership, που κληρονομούν από την βασική κλάση User.

Η εγγραφή διαχωρίζεται ανάλογα με τον τύπο του χρήστη και αποθηκεύει τα δεδομένα στον κατάλληλο πίνακα της βάσης δεδομένων.

Δομή Βάσης Δεδομένων

Πίνακας users (Base Table)

Στήλη	Τύπος	Περιγραφή
id	        BIGINT	        Πρωτεύον Κλειδί
username	VARCHAR(50)  	Όνομα Χρήστη
password	VARCHAR(255)	Κωδικός Πρόσβασης
email	    VARCHAR(100)	Email Χρήστη

Πίνακας citizen

Στήλη	Τύπος	        Περιγραφή
id	    BIGINT	         Ξένο Κλειδί (User)
name	VARCHAR(100)	Όνομα Χρήστη

Πίνακας dealership

Στήλη	      Τύπος   	    Περιγραφή
id	          BIGINT	    Ξένο Κλειδί (User)
name	      VARCHAR(100)	Όνομα Αντιπροσωπείας
location	  VARCHAR(255)	Τοποθεσία
contact_info  VARCHAR(100)	Στοιχεία Επικοινωνίας

Endpoints

1. Εγγραφή Citizen
   •	URL: POST /users/register/citizen
   •	Request Body:

{
"username": "johnDoe",
"password": "password123",
"email": "john@example.com",
"name": "John Doe"
}

	•	Response:

{
"id": 1,
"username": "johnDoe",
"email": "john@example.com",
"name": "John Doe"
}

2. Εγγραφή Dealership
   •	URL: POST /users/register/dealership
   •	Request Body:

{
"username": "bestDealership",
"password": "securePassword",
"email": "dealership@example.com",
"name": "Best Dealership",
"location": "123 Main Street",
"contactInfo": "123-456-7890"
}

	•	Response:

{
"id": 2,
"username": "bestDealership",
"email": "dealership@example.com",
"name": "Best Dealership",
"location": "123 Main Street",
"contactInfo": "123-456-7890"
}

Υλοποίηση

1. Κλάσεις Μοντέλου

Οι κλάσεις User, Citizen, και Dealership ακολουθούν την αρχιτεκτονική κληρονομικότητας με @Inheritance(strategy = InheritanceType.JOINED).

2. Repository Classes
   •	UserRepository: Διαχειρίζεται τις βασικές λειτουργίες για την κλάση User.
   •	CitizenRepository: Επεκτείνει το JpaRepository για την κλάση Citizen.
   •	DealershipRepository: Επεκτείνει το JpaRepository για την κλάση Dealership.

3. Υπηρεσία (Service)

Η υπηρεσία UserService περιλαμβάνει τις μεθόδους:
•	registerCitizen
•	registerDealership

4. Controller

Το UserController υλοποιεί τα endpoints για την εγγραφή.

Προϋποθέσεις
1.	Βάση Δεδομένων:
•	Η MySQL βάση δεδομένων πρέπει να περιλαμβάνει τους πίνακες users, citizen, και dealership.
2.	Spring Security:
•	Τα endpoints /users/register/** πρέπει να είναι ελεύθερα προσβάσιμα.
3.	Password Encoding:
•	Χρήση PasswordEncoder για την κρυπτογράφηση κωδικών.

Πώς να Δοκιμάσετε
1.	Εκκινήστε την εφαρμογή.
2.	Χρησιμοποιήστε το Postman ή άλλο εργαλείο HTTP για να στείλετε αιτήματα στα παραπάνω endpoints.
3.	Ελέγξτε τη βάση δεδομένων για τις νέες εγγραφές στους πίνακες citizen και dealership.

Αυτή η λειτουργία σας επιτρέπει να προσθέσετε δυναμικά νέους χρήστες (citizen και dealership) με τη χρήση κληρονομικότητας και καθαρής αρχιτεκτονικής.