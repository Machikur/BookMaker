function checkIfPasswordsEqualsAndHaveMoreThan5Signs() {
    const password = document.getElementById("password1");
    const passwordRepeat = document.getElementById("password2");
    return password.value !== passwordRepeat.value || password.length < 5;
}

function unlockNewUserButton() {
    const button = document.getElementById("newUserButton");
    const error = document.getElementById("errorMessage");
    if (checkIfPasswordsEqualsAndHaveMoreThan5Signs() === true) {
        button.disabled = true;
        error.innerHTML = "Podane hasła nie są takie same lub posiadają za mało znaków";
    } else {
        button.disabled = false;
        error.innerHTML = "";
    }
}