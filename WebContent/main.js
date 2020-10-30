$(document).ready(function() {
    $("#login").hide();
    $("#login").fadeIn(1000);
    $("#registration").hide();
    $("#registration").fadeIn(1000);

    $("#yes").click(function() {
      $('#payment').prop("disabled", false);
    })

    $("#no").click(function() {
      $('#payment').prop("disabled", true);
    })

    $("#enter-shipping").click(function() {
      if ($(this).is(':checked')) {
        $('#shipping').prop("disabled", false);
      }

      else {
        $('#shipping').prop("disabled", true);
      }
    })
});

(function() {
    'use strict';
    window.addEventListener('load', function() {
      // Fetch all the forms we want to apply custom Bootstrap validation styles to
      var forms = document.getElementsByClassName('needs-validation');
      var password = document.getElementById("inputPassword");
      var confirmPassword = document.getElementById("inputConfirmPassword");

      $('.alert').hide();

      // Loop over them and prevent submission
      var validation = Array.prototype.filter.call(forms, function(form) {
        form.addEventListener('submit', function(event) {
          if (password.value != confirmPassword.value) {
            event.preventDefault();
            event.stopPropagation();
          }

          else if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
          }

          if (password.value != confirmPassword.value) {
            $('.alert').show();
            form.classList.remove('was-validated');
          }

          else {
            form.classList.add('was-validated');
            $('.alert').hide();
          }
        }, false);
      });
    }, false);
})();
