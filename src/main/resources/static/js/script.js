function editCar(event) {
    const carId = $(event.target).val();
    window.location.href = `/dealership/dashboard/edit-car?carId=${carId}`;
}

function applySearch() {
    const query = document.getElementById('search-input').value.toLowerCase();
    const carCards = document.querySelectorAll('.card');

    carCards.forEach(card => {
        const text = card.innerText.toLowerCase();
        card.style.display = text.includes(query) ? '' : 'none';
    });
}

function applyFilters() {
    const priceRange = document.getElementById('price-range').value;
    const seats = document.getElementById('seats').value;
    const fuelType = document.getElementById('fuel-type').value;

    const carCards = document.querySelectorAll('.card');

    carCards.forEach(card => {
        const price = parseInt(card.querySelector('.price').innerText.replace('$', ''));
        const carSeats = parseInt(card.querySelector('.seats').innerText.replace('Seats: ', ''));
        const carFuelType = card.querySelector('.fuel-type').innerText.replace('Fuel Type: ', '');

        let isVisible = true;

        if (priceRange) {
            const [min, max] = priceRange.split('-').map(Number);
            isVisible = isVisible && price >= min && price <= max;
        }

        if (seats) {
            isVisible = isVisible && carSeats === parseInt(seats);
        }

        if (fuelType) {
            isVisible = isVisible && carFuelType.toLowerCase() === fuelType;
        }

        card.style.display = isVisible ? '' : 'none';
    });
}

function buyCar(event) {
  const button = $(event.target);
    const carId = button.data('id');
    const stockQuantity = button.data('stock');

    if (stockQuantity >= 1) {
        window.location.href = `/customer/dashboard/cars/purchase?carId=${carId}`;
    } else {
        alert("There is not any available car of this model for the moment. We will notify you when it is available again for a purchase.");
    }
}

function testDrive(event) {
    const button = $(event.target);
    const carId = button.data('id');
    const stockQuantity = button.data('stock');

    if (stockQuantity >= 1) {
        window.location.href = `/customer/dashboard/cars/test-drive?carId=${carId}`;
    } else {
        alert("There is not any available car of this model for the moment. We will notify you when it is available again for a test drive.");
    }
}

$(document).ready(function() {
    $('#customer-signup').submit(function(event) {
        event.preventDefault();

        if ($('#password').val() !== $('#repeat-password').val()) {
            alert('Your passwords do not match.');
            return;
        }

        var formData = {
            username: $('#username').val(),
            firstName: $('#first-name').val(),
            lastName: $('#last-name').val(),
            taxNumber: $('#tax-number').val(),
            email: $('#email').val(),
            password: $('#password').val(),
        };

        $.ajax({
            url: '/register/customer',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(response) {
                window.location.href = '/login';
                alert(response);
            },
            error: function(xhr, status, error) {
                const messages = xhr.responseText.split(', ');
                const alertMessage = messages.join('\n');
                alert(alertMessage);
            }
        });
    });

    $('#add-a-car').submit(function(event) {
        event.preventDefault();

        var formData = {
            brand: $('#brand').val(),
            model: $('#model').val(),
            fuelType: $('#fuel-type').val(),
            engine: $('#engine').val(),
            seats: $('#seats').val(),
            price: $('#price').val(),
            additionalInfo: $('#additional-info').val(),
            stockQuantity: $('#quantity').val(),
        };

        const dealershipId = $('#dealership-id').val();
        const carId = $('#car-id').val();
        const url = carId
            ? `/dealership/dashboard/edit-car?carId=${carId}`
            : `/dealership/dashboard/add-car?dealershipId=${dealershipId}`;

        $.ajax({
            url: url,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(response) {
                window.location.href = '/dealership/dashboard/cars';
                alert(response);
            },
            error: function(xhr, status, error) {
                const messages = xhr.responseText.split(', ');
                const alertMessage = messages.join('\n');
                alert(alertMessage);
            }
        });
    });

    $('#buy-car-form').submit(function(event) {
         event.preventDefault();
         const totalCost = $('#car-total-cost').val().replace("$", "");
         const carId = $('#car-id').val();
         const customerId = $('#customer-id').val();
         const today = new Date();
         const todayFormatted = today.toISOString().split('T')[0];

         var formData = {
             amount: totalCost,
             purchaseDate: todayFormatted
         };

         $.ajax({
             url: '/customer/dashboard/cars/purchase?carId=' + carId + '&customerId=' + customerId,
             type: 'POST',
             contentType: 'application/json',
             data: JSON.stringify(formData),
             success: function(response) {
                 window.location.href = '/customer/dashboard/purchases';
                 alert(response);
             },
             error: function(xhr, status, error) {
                 const messages = xhr.responseText.split(', ');
                 const alertMessage = messages.join('\n');
                 alert(alertMessage);
             }
         });
    });

    $('#book-a-test-drive-form').submit(function(event) {
         event.preventDefault();
         const dateTime = $('#test-drive-date').val();
         const carId = $('#car-id').val();
         const customerId = $('#customer-id').val();

         const [date, time] = dateTime.split('T');

         var formData = {
             date: date,
             time: time
         };

         $.ajax({
             url: '/customer/dashboard/cars/test-drive?carId=' + carId + '&customerId=' + customerId,
             type: 'POST',
             contentType: 'application/json',
             data: JSON.stringify(formData),
             success: function(response) {
                 window.location.href = '/customer/dashboard/test-drive-bookings';
                 alert(response);
             },
             error: function(xhr, status, error) {
                 const messages = xhr.responseText.split(', ');
                 const alertMessage = messages.join('\n');
                 alert(alertMessage);
             }
         });
    });

    $('#dealership-signup').submit(function(event) {
        event.preventDefault();

        if ($('#password').val() !== $('#repeat-password').val()) {
            alert('Your passwords do not match.');
            return;
        }

        var formData = {
            username: $('#username').val(),
            name: $('#name').val(),
            taxNumber: $('#tax-number').val(),
            email: $('#email').val(),
            password: $('#password').val(),
        };

        $.ajax({
            url: '/register/dealership',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function(response) {
                window.location.href = '/login';
                alert(response);
            },
            error: function(xhr, status, error) {
                const messages = xhr.responseText.split(', ');
                const alertMessage = messages.join('\n');
                alert(alertMessage);
            }
        });
    });
});