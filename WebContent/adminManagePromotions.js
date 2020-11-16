var imageFile = document.getElementById("filePhoto");
var preview = document.getElementById("added-picture");

$(document).ready(function() {
    document.getElementById('upload-image').addEventListener('click', openUploadWindow);

    imageFile.addEventListener("change", function() {
        changeImage(this);
    });
});

function openUploadWindow() {
    document.getElementById('filePhoto').click();
}

function changeImage(input) {
    var reader;

    var image = input.files[0];

    if (image.type.indexOf('image/') !== 0) {
        alert("Invalid image file");
    }

    else if (input.files && input.files[0]) {
        reader = new FileReader();

        reader.onload = function(e) {
            preview.setAttribute('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}