import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/listeenvies")
public class ListeEnviesController {

    private final ListeEnviesService listeEnviesService;

    @Autowired
    public ListeEnviesController(ListeEnviesService listeEnviesService) {
        this.listeEnviesService = listeEnviesService;
    }

    // Obtenir la liste d'envies d'un utilisateur
    @GetMapping("/{utilisateurId}")
    public ListeEnvies obtenirListeEnvies(@PathVariable Long utilisateurId) {
        return listeEnviesService.obtenirListeEnvies(utilisateurId);
    }

    // Ajouter un produit à la liste d'envies
    @PostMapping("/ajouter")
    public ListeEnvies ajouterProduitAListeEnvies(@RequestParam Long utilisateurId, @RequestParam Long produitId) {
        return listeEnviesService.ajouterProduitAListeEnvies(utilisateurId, produitId);
    }

    // Retirer un produit de la liste d'envies
    @PostMapping("/enlever")
    public ListeEnvies enleverProduitDeListeEnvies(@RequestParam Long utilisateurId, @RequestParam Long produitId) {
        return listeEnviesService.enleverProduitDeListeEnvies(utilisateurId, produitId);
    }
}
