document.getElementById("mouvement-form").addEventListener("submit", function(e) {
    e.preventDefault();

    const mouvement = {
        produitId: parseInt(document.getElementById("produit_id").value),
        sourceType: document.getElementById("source_type").value,
        sourceId: parseInt(document.getElementById("source_id").value),
        destinationType: document.getElementById("destination_type").value,
        destinationId: parseInt(document.getElementById("destination_id").value),
        quantite: parseInt(document.getElementById("quantite").value)
    };

    fetch("http://localhost:8083/api/mouvements", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(mouvement)
    })
    .then(res => {
        if (!res.ok) throw new Error("Erreur lors du mouvement de stock");
        return res.json();
    })
    .then(data => {
        alert("Mouvement de stock effectué avec succès !");
        console.log(data);
    })
    .catch(err => {
        console.error("Erreur:", err);
        alert("Erreur lors du mouvement de stock");
    });
});
