import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListeEnviesService {

    private final ListeEnviesRepository listeEnviesRepository;
    private final ProduitRepository produitRepository;

    @Autowired
    public ListeEnviesService(ListeEnviesRepository listeEnviesRepository, ProduitRepository produitRepository) {
        this.listeEnviesRepository = listeEnviesRepository;
        this.produitRepository = produitRepository;
    }

    // Récupérer la liste d'envies d'un utilisateur
    public ListeEnvies obtenirListeEnvies(Long utilisateurId) {
        return listeEnviesRepository.findByUtilisateurId(utilisateurId);
    }

    // Ajouter un produit à la liste d'envies
    public ListeEnvies ajouterProduitAListeEnvies(Long utilisateurId, Long produitId) {
        ListeEnvies listeEnvies = listeEnviesRepository.findByUtilisateurId(utilisateurId);
        Produit produit = produitRepository.findById(produitId).orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        listeEnvies.ajouterProduit(produit);
        return listeEnviesRepository.save(listeEnvies);
    }

    // Retirer un produit de la liste d'envies
    public ListeEnvies enleverProduitDeListeEnvies(Long utilisateurId, Long produitId) {
        ListeEnvies listeEnvies = listeEnviesRepository.findByUtilisateurId(utilisateurId);
        Produit produit = produitRepository.findById(produitId).orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        listeEnvies.enleverProduit(produit);
        return listeEnviesRepository.save(listeEnvies);
    }
}
