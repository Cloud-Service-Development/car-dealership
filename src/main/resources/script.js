document.getElementById('addCarForm').addEventListener('submit', async function(event) {
    event.preventDefault(); // Αποτροπή της ανανέωσης της σελίδας
   const dealershipId = document.getElementById('dealershipId').value;
    
    //ελεγχος αν το dealershipId ειναι αδειο
    if (!dealershipId) {
        document.getElementById('responseMessage').textContent = 'Το Dealership ID είναι άδειο!';
         document.getElementById('responseMessage').style.color = 'red';
      return;
      }
    const carData = {
      brand: document.getElementById('brand').value,
      model: document.getElementById('model').value,
      fuelType: document.getElementById('fuelType').value,
      thesis: parseInt(document.getElementById('thesis').value),
      numberOfSameCars:parseInt(document.getElementById("numberOfSameCars").value),
      price: parseFloat(document.getElementById('price').value),
    };
  
    console.log('Collected Car Data: ',carData);
    console.log('DealershipId ID: ',dealershipId);

    try {
      const response = await fetch(`http://localhost:8080/cars/add?dealershipId=${dealershipId}`, {
        method: "POST",
        headers: {
          'Content-Type': 'application/json','Access-Control-Allow-Headers':
          'Content-Type,X-Amz-Date,Authorization,X-Api-Key,Xamz-Security-Token',
          'Access-Control-Allow-Methods':'OPTIONS,POST',
          'Access-Control-Allow-Credentials':true,
          'Access-Control-Allow-Origin':'*',
          'X-Requested-With':'*'
        },
        body: JSON.stringify(carData),
      });
  
      const responseData = await response.json();
  
      console.log('Dealership ID:', dealershipId);
      
      if (response.ok) {
        document.getElementById('responseMessage').textContent = 'Το αυτοκίνητο προστέθηκε επιτυχώς!';
        document.getElementById('responseMessage').style.color = 'green';
      } else {
        document.getElementById('responseMessage').textContent = `Σφάλμα: ${responseData.message}`;
      }
    } catch (error) {
      document.getElementById('responseMessage').textContent = 'Σφάλμα σύνδεσης!';
    }
  });