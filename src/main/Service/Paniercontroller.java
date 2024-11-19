import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/panier")
public class PanierController {

    private final PanierService panierService;

    @Autowired
    public PanierController(PanierService panierService) {
        this.panierService = panierService;
    }

    // Ajouter un produit au panier
    @PostMapping("/ajouter")
    public Panier ajouterProduitAuPanier(@RequestParam Long utilisateurId, @RequestParam Long produitId) {
        return panierService.ajouterProduitAuPanier(utilisateurId, produitId);
    }

    // Retirer un produit du panier
    @PostMapping("/enlever")
    public Panier enleverProduitDuPanier(@RequestParam Long utilisateurId, @RequestParam Long produitId) {
        return panierService.enleverProduitDuPanier(utilisateurId, produitId);
    }

    // Obtenir le panier d'un utilisateur
    @GetMapping("/{utilisateurId}")
    public Panier obtenirPanier(@PathVariable Long utilisateurId) {
        return panierService.obtenirPanier(utilisateurId);
    }
}
