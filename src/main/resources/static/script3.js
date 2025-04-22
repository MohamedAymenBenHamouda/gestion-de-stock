// Fonction pour afficher un article dans la table
function ajouterArticle(article) {
    const tableBody = document.getElementById('table-body');
    const row = document.createElement('tr');

    row.innerHTML = `
        <td>${article.id}</td>
        <td>${article.entrepot.id}</td>
        <td>${article.nom}</td>
        <td>${article.quantite}</td>
        <td>${article.typeLieu}</td>

    `;

    tableBody.appendChild(row);
}

// Fonction pour charger les articles depuis la base de données

function chargerArticles() {
    fetch("http://localhost:8083/api/stocks")
        .then(response => {
            if (!response.ok) {
                throw new Error("Erreur lors de la récupération des articles.");
            }
            return response.json();
        })
        .then(data => {
            data.forEach(article => {
                ajouterArticle(article);
            });
        })
        .catch(error => {
            console.error("Erreur:", error);
        });
}


// Écouteur pour l'ajout d'un produit
document.getElementById('ajouter').addEventListener('click', function () {
    const id = document.getElementById('ID').value;
    const entrepotId = document.getElementById('ID-entrepot').value;
    const produitId = document.getElementById('produit_id').value;
    const nom = document.getElementById('nom').value;
    const quantite = parseInt(document.getElementById('quantite').value);
    const typeLieu = document.getElementById('typeLieu').value;


    if (!entrepotId || !produitId || !nom ||!typeLieu || isNaN(quantite)) {
        alert('Veuillez remplir tous les champs correctement.');
        return;
    }

    const nouvelArticle = {
        produit: { id: parseInt(produitId) },
        entrepot: { id: parseInt(entrepotId) },
        nom: nom,
        quantite: quantite,
        typeLieu: typeLieu
    };

    fetch("http://localhost:8083/api/stocks", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(nouvelArticle)
    })
    .then(response => {
        if (!response.ok) {
            return response.text().then(text => { throw new Error(text); });
        }
        return response.json();
    })
    .then(data => {
        console.log("Produit ajouté avec succès:", data);
        ajouterArticle(data);
    })
    .catch(error => {
        console.error("Erreur:", error);
    });

    // Réinitialisation des champs
    document.getElementById('ID').value = '';
    document.getElementById('ID-entrepot').value = '';
    document.getElementById('produit_id').value = ''; 
    document.getElementById('nom').value = '';
    document.getElementById('quantite').value = '1';
    document.getElementById('typeLieu').value = '';

});

// Charger les articles à l'ouverture de la page
document.addEventListener('DOMContentLoaded', chargerArticles);
