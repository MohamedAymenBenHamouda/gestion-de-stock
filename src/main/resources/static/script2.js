// Fonction pour ajouter un produit à la table HTML
function ajouterArticle(article) {
    const tableBody = document.getElementById('table-body');
    const row = document.createElement('tr');

    row.innerHTML = `
        <td>${article.id}</td>
        <td>${article.nom}</td>
        <td>${article.categorie}</td>
        <td>${article.quantite}</td>
        <td>${article.prix} €</td>
        <td>
            <button class="edit-btn" onclick="modifierArticle(${article.id})">✎</button>
            <button class="delete-btn" onclick="supprimerArticle(${article.id})">🗑</button>
        </td>
    `;

    tableBody.appendChild(row);
}
function modifierArticle(id) {
    fetch(`http://localhost:8083/api/produits/${id}`)
    .then(response => response.json())
    .then(produit => {
        document.getElementById('ID').value = produit.id;
        document.getElementById('nom').value = produit.nom;
        document.getElementById('categorie').value = produit.categorie;
        document.getElementById('quantite').value = produit.quantite;
        document.getElementById('prix').value = produit.prix;

        // Changer le bouton pour indiquer qu'on modifie
        const bouton = document.getElementById('ajouter');
        bouton.innerText = "Modifier";
        bouton.onclick = function () {
            enregistrerModification(produit.id);
        };
    })
    .catch(error => console.error("Erreur lors du chargement du produit :", error));
}

function enregistrerModification(id) {
    const produitModifie = {
        id: id,
        nom: document.getElementById('nom').value,
        categorie: document.getElementById('categorie').value,
        quantite: parseInt(document.getElementById('quantite').value),
        prix: parseFloat(document.getElementById('prix').value)
    };

    fetch(`http://localhost:8083/api/produits/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(produitModifie)
    })
    .then(response => response.json())
    .then(data => {
        alert("Produit modifié avec succès !");
        // Mettre à jour manuellement la ligne dans le tableau
        const tableBody = document.getElementById('table-body');
        tableBody.innerHTML = ""; // Nettoyer le tableau
        // Recharger les produits
        fetch("http://localhost:8083/api/produits")
            .then(response => response.json())
            .then(data => {
                data.forEach(article => {
                    if (article && article.id && article.nom) {
                        ajouterArticle(article);
                    }
                });
            });
    })
    
    .catch(error => console.error("Erreur lors de la modification du produit :", error));
}

function supprimerArticle(id) {
    if (confirm("Voulez-vous vraiment supprimer ce produit ?")) {
        fetch(`http://localhost:8083/api/produits/${id}`, {
            method: "DELETE"
        })
        .then(response => {
            if (!response.ok) {
                throw new Error("Erreur lors de la suppression du produit.");
            }
            alert("Produit supprimé avec succès !");
            location.reload();
        })
        .catch(error => console.error("Erreur lors de la suppression du produit :", error));
    }
}

// Fonction pour ajouter un stock à la table HTML
function ajouterStock(stock) {
    const stockTable = document.getElementById('stock-table-body');
    const row = document.createElement('tr');

    row.innerHTML = `
        <td>${stock.produit.nom}</td>
        <td>${stock.entrepot.nom}</td>
        <td>${stock.quantite}</td>
    `;

    stockTable.appendChild(row);
}

// Initialisation du tableau au chargement de la page
window.onload = function () {
    document.getElementById('table-body').innerHTML = ""; // Nettoyage du tableau
};

// Écouteur pour l'ajout d'un produit
document.getElementById('ajouter').addEventListener('click', function () {
    const nom = document.getElementById('nom').value;
    const categorie = document.getElementById('categorie').value;
    const quantite = parseInt(document.getElementById('quantite').value);
    const prix = parseFloat(document.getElementById('prix').value);

    // Vérification des champs
    if (!nom || !categorie || isNaN(quantite) || isNaN(prix)) {
        alert('Veuillez remplir tous les champs correctement.');
        return;
    }

    // Création d'un objet produit
    const nouvelArticle = {
        nom: nom,
        categorie: categorie,
        quantite: quantite,
        prix: prix
    };

    // Envoi au backend pour ajouter le produit
    fetch("http://localhost:8083/api/produits", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(nouvelArticle)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Erreur lors de l'ajout du produit");
        }
        return response.json();
    })
    .then(data => {
        console.log("Produit ajouté avec succès:", data);
        ajouterArticle(data); // Afficher le produit dans la table

        // Ajout du stock pour ce produit
        const entrepotId = 1; // Assurez-vous que l'ID de l'entrepôt est valide

        console.log("Ajout du stock avec entrepotId:", entrepotId, "produitId:", data.id);
        
        return fetch("http://localhost:8083/api/stocks", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                entrepotId: entrepotId,
                produitId: data.id,
                quantite: data.quantite
            })
        });
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Erreur lors de l'ajout du stock");
        }
        return response.json();
    })
    .then(stock => {
        console.log("Stock mis à jour:", stock);
        ajouterStock(stock);
    })
    .catch(error => {
        console.error("Erreur:", error);
        
    });

    // Réinitialisation du formulaire
    document.getElementById('nom').value = '';
    document.getElementById('categorie').value = '';
    document.getElementById('quantite').value = '1';
    document.getElementById('prix').value = '';
});

// Initialisation du tableau au chargement de la page
window.onload = function () {
    document.getElementById('table-body').innerHTML = ""; // Nettoyage du tableau

    // Appel à l'API pour récupérer tous les produits
    fetch("http://localhost:8083/api/produits")
        .then(response => {
            if (!response.ok) {
                throw new Error("Erreur lors du chargement des produits");
            }
            return response.json();
        })
        .then(data => {
            data.forEach(article => {
                ajouterArticle(article); // Affiche chaque produit dans le tableau
            });
        })
        .catch(error => {
            console.error("Erreur :", error);
        });
};
