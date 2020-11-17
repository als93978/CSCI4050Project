var imageFile = document.getElementById("filePhoto");
var preview = document.getElementById("added-picture");

$(document).ready(function() {
    document.getElementById('upload-image').addEventListener('click', openUploadWindow);

    imageFile.addEventListener("change", function() {
        changeImage(this);
    });

    $("#add-book").click(function(event) {
        if (imageFile.files.length == 0) {
            event.preventDefault();
            event.stopPropagation();
            alert("No image file found.")
        }    
    })
});

function openUploadWindow() {
    imageFile.click();
}

function changeImage(input) {
    var reader;

    var image = input.files[0];

    if (image.type.indexOf('image/') !== 0) {
        alert("Invalid image file or no image file detected");
    }

    else if (input.files && input.files[0]) {
        reader = new FileReader();

        reader.onload = function(e) {
            preview.setAttribute('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}