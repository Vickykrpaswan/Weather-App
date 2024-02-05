document.addEventListener("DOMContentLoaded", function() {
    var weatherIcon = document.getElementById("weather-icon");

	var val = document.getElementById("wc").value;
		
	  switch (val) {
      case 'Clouds':
          weatherIcon.src = "/image/cloudy1.png";
          break;
      case 'Clear':
          weatherIcon.src = "/image/clear.jpg";
          break;
      case 'Rain':
          weatherIcon.src = "/image/rain.png";
          break;
      case 'Mist':
          weatherIcon.src = "/image/mist.png";
          break;
      case 'Snow':
          weatherIcon.src = "/image/snow.png";
          break;
      case 'Haze':
          weatherIcon.src = "/image/haze.png";
          break;
      case 'Fog':
          weatherIcon.src="/image/image/fog.png";
          break;
      default:
          weatherIcon.src="/image/default.png";
          console.log("Unknown weather condition");
          break;
          }
       });
           