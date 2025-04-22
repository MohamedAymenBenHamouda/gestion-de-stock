document.addEventListener('DOMContentLoaded', () => {
    fetch('http://localhost:8083/api/transfert')
        .then(response => response.json())
        .then(data => {
            console.log("Transferts reçus :", data); // 🔍 debug

            const tableBody = document.querySelector('#transfert-table tbody');
            tableBody.innerHTML = ''; // Reset

            if (data.length === 0) {
                tableBody.innerHTML = '<tr><td colspan="4">Aucun transfert trouvé</td></tr>';
                return;
            }

            data.forEach(transfert => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${transfert.id}</td>
                    <td>${transfert.entrepot ? transfert.entrepot.id : '—'}</td>
                    <td>${transfert.produit ? transfert.produit.nom : '—'}</td>
                    <td>${transfert.quantite}</td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Erreur lors du chargement des transferts :', error);
        });
});
