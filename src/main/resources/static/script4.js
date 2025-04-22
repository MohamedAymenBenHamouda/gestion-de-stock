function transfertStock() {
  const produitId = document.getElementById('produitId').value;
  const entrepotId = document.getElementById('entrepotId').value;
  const pointDeVenteId = document.getElementById('pointDeVenteId').value;
  const quantite = document.getElementById('quantite').value;

  if (!produitId || !entrepotId || !pointDeVenteId || !quantite) {
      alert("Veuillez remplir tous les champs.");
      return;
  }

  fetch('http://localhost:8083/api/transfert/entrepot-to-pdv', {
      method: 'POST',
      headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
      },
      body: `produitId=${produitId}&entrepotId=${entrepotId}&pointDeVenteId=${pointDeVenteId}&quantite=${quantite}`
  })
  .then(response => {
      if (!response.ok) {
          return response.text().then(text => { throw new Error(text) });
      }
      return response.text();
  })
  .then(message => {
      alert("✅ Transfert effectué avec succès !");
  })
  .catch(error => {
      console.error('Erreur de transfert :', error);
      alert("❌ Échec du transfert : " + error.message);
  });
}
