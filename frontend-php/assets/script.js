function validateOrderForm(){
    let quantityInput = document.getElementById("quantity").value;
    let qty = parseInt(quantityInput);

    if (isNaN(qty)) {
        alert("Kuantitas harus berupa angka.");
        return false;
    }

    if (qty < 1) {
        alert("Kuantitas minimum adalah 1.");
        return false;
    }

    if (qty > 20) {
        alert("Kuantitas maksimum yang diizinkan adalah 20.");
        return false;
    }

    return true;
}