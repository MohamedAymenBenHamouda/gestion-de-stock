function ajouterArticle(article) {
    const tableBody = document.getElementById('table-body');
    const row = document.createElement('tr');

    row.innerHTML = `
        <td>${article.id}</td>
        <td>${article.entrepotId}</td>
        <td>${article.nom}</td>
        <td>${article.quantite}</td>
    `;

    tableBody.appendChild(row);
}

// Écouteur pour l'ajout d'un produit
document.getElementById('ajouter').addEventListener('click', function () {
    const id = document.getElementById('ID').value;
    const entrepotId = document.getElementById('ID_entrepot').value;
    const nom = document.getElementById('nom').value;
    const quantite = parseInt(document.getElementById('quantite').value);

    if (!id || !entrepotId || !nom || isNaN(quantite)) {
        alert('Veuillez remplir tous les champs correctement.');
        return;
    }

    const nouvelArticle = {
        id: id,
        entrepotId: entrepotId,
        nom: nom,
        quantite: quantite
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
            throw new Error("Erreur lors de l'ajout du produit");
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
 

    document.getElementById('ID').value = '';
    document.getElementById('ID_entrepot').value = '';
    document.getElementById('nom').value = '';
    document.getElementById('quantite').value = '1';
});