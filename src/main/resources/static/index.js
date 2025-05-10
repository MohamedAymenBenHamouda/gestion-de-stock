document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const adresseEmail = document.getElementById('email').value;
    const motDePasse = document.getElementById('motDePasse').value;

    fetch('http://localhost:8083/utilisateurs/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ adresseEmail, motDePasse })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Email ou mot de passe incorrect");
        }
        return response.json();
    })
    .then(utilisateur => {
        console.log('Connexion réussie:', utilisateur);

        // 🔀 Redirection selon le rôle
        if (utilisateur.role === "ADMIN_ENTREPOT") {
            window.location.href = "home.html";
        } else if (utilisateur.role === "RESPONSABLE_POINT_DE_VENTE") {
            window.location.href = "point_de_vente.html";
        } else {
            document.getElementById('error-message').textContent = "Rôle inconnu.";
        }
    })
    .catch(error => {
        document.getElementById('error-message').textContent = error.message;
    });
});
