package Model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class PostDAO {
    
    public PostDAO(PessoaDAO pessoaDAO) {

    }
    
    private List<Post>listaDePost = new LinkedList();

    /*private Post vetordePosts[] = new Post[50];
    
    public PostDAO(PessoaDAO pessoaDAO) {
        Pessoa pessoa = pessoaDAO.buscaPessoaPorNome("Alberto");
        Post post1 = new Post(pessoa, "olaaaaa");
        inserirPost(post1);
    } 
    
    public boolean inserirPost(Post post) {

        for (int i = 0; i < vetordePosts.length; i++) {

            if (vetordePosts[i] == null) {
                vetordePosts[i] = post;
                return true;
            }

        }
        return false;
    }

    public void verMeusPosts(Pessoa pessoa) {
        boolean encontrouPosts = false; 

        for (Post post : vetordePosts) {
        if (post != null && post.getPessoa().equals(pessoa)) {
                System.out.println(post + "\n");
                encontrouPosts = true; 
            }
        }
        if (!encontrouPosts) {
            System.out.println("Você ainda não postou nada!");
        }
    }*/

}
