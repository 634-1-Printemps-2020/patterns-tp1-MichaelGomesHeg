package metier;

import outils.Labyrinthe;

import java.util.*;

public class PyRat {

    /* Méthode appelée une seule fois permettant d'effectuer des traitements "lourds" afin d'augmenter la performace de la méthode turn. */
    public void preprocessing(Map<Point, List<Point>> laby, int labyWidth, int labyHeight, Point position, List<Point> fromages) {
    }

    /* Méthode de test appelant les différentes fonctionnalités à développer.
        @param laby - Map<Point, List<Point>> contenant tout le labyrinthe, c'est-à-dire la liste des Points, et les Points en relation (passages existants)
        @param labyWidth, labyHeight - largeur et hauteur du labyrinthe
        @param position - Point contenant la position actuelle du joueur
        @param fromages - List<Point> contenant la liste de tous les Points contenant un fromage. */
    public void turn(Map<Point, List<Point>> laby, int labyWidth, int labyHeight, Point position, List<Point> fromages) {
        Point pt1 = new Point(2,1);
        Point pt2 = new Point(3,1);
        System.out.println((fromageIci(pt2) ? "Il y a un" : "Il n'y a pas de") + " fromage ici, en position " + pt2);
        System.out.println((fromageIci_EnOrdreConstant(pt2) ? "Il y a un" : "Il n'y a pas de") + " fromage ici, en position " + pt2);
        System.out.println((passagePossible(pt1, pt2, laby) ? "Il y a un" : "Il n'y a pas de") + " passage de " + pt1 + " vers " + pt2);
        System.out.println((passagePossible_EnOrdreConstant(pt1, pt2, laby) ? "Il y a un" : "Il n'y a pas de") + " passage de " + pt1 + " vers " + pt2);
        System.out.println("Liste des points inatteignables depuis la position " + position + " : " + pointsInatteignables(position, laby));
    }

    /* Regarde dans la liste des fromages s’il y a un fromage à la position pos.
        @return true s'il y a un fromage à la position pos, false sinon. */
    private boolean fromageIci(Point pos) {
        for (int i = 0; i < Labyrinthe.getFromages().size(); i++) {
            if (Labyrinthe.getFromages().get(i).equals(pos)){
                return true;
            }
        }
        return false;
    }

    /* Regarde de manière performante (accès en ordre constant) s’il y a un fromage à la position pos.
        @return true s'il y a un fromage à la position pos, false sinon. */
    private boolean fromageIci_EnOrdreConstant(Point pos) {
        if(matrice(pos)[5]){
            return true;
        }
        return false;
    }

    private Set<Point> col (){
        HashSet set = new HashSet();
        for (int i = 0; i < Labyrinthe.getFromages().size(); i++) {
            set.add(Labyrinthe.getFromages().get(i));
        }
        return set;
    }

    private Boolean[] matrice(Point pos){
        Boolean tab[] = new Boolean[Labyrinthe.getFromages().size()];
        for (int i = 0; i < Labyrinthe.getFromages().size(); i++) {
            if(Labyrinthe.getFromages().get(i).equals(pos)){
                tab[i] = true;
            }
            else{
                tab[i] = false;
            }
        }
        return tab;
    }

    /* Indique si le joueur peut passer de la position (du Point) « de » au point « a ».
        @return true s'il y a un passage depuis  « de » vers « a ». */
    private boolean passagePossible(Point de, Point a, Map<Point, List<Point>> laby) {
        for (Point el : laby.get(de)){
            if(el.equals(a)){
                return true;
            }
        }
        return false;
    }

    /* Indique si le joueur peut passer de la position (du Point) « de » au point « a »,
        mais sans devoir parcourir la liste des Points se trouvant dans la Map !
        @return true s'il y a un passage depuis  « de » vers « a ». */
    /* Indique si le joueur peut passer de la position (du Point) « de » au point « a »,
    mais sans devoir parcourir la liste des Points se trouvant dans la Map !
    @return true s'il y a un passage depuis  « de » vers « a ». */
    private boolean passagePossible_EnOrdreConstant(Point de, Point a, Map<Point, List<Point>> laby) {
        if(collectionMaker(de, a, laby).contains(a)){
            return true;
        };
        return false;
    }

    private Set collectionMaker(Point de, Point a, Map<Point, List<Point>> laby){
        /***
         * Création de la collection
         */
        Set<Point> innerMap = new HashSet<>();
        for(Point el : laby.get(de)){
            innerMap.add(el);
        }
        return innerMap;
    }

    /* Retourne la liste des points qui ne peuvent pas être atteints depuis la position « pos ».
        @return la liste des points qui ne peuvent pas être atteints depuis la position « pos ». */
    private List<Point> pointsInatteignables(Point pos, Map<Point, List<Point>> laby) {
         HashSet<Point> routage = new HashSet<>();
         ArrayList<Point> marked = new ArrayList<>();
         return _dfs(laby, pos, routage, marked);
    }

    private List<Point> _dfs(Map<Point, List<Point>> laby, Point pos, HashSet<Point> routage, ArrayList<Point> marked) {
        marked.add(pos);
        for (int i = 0; i < laby.size(); i++) {
           if(!marked.contains(laby.get(i))){
               routage.add(pos);
               return _dfs(laby, (Point) laby.get(i), routage ,marked);
           }

        }
        return null;
    }

    /* Retourne la liste des points qui ne peuvent pas être atteints depuis la position « pos ».
    @return la liste des points qui ne peuvent pas être atteints depuis la position « pos ». */
    /*private ArrayList<Object> pointsInatteignables(Point pos, Map<Point, List<Point>> laby) {
        System.out.print(laby);
        ArrayList<Object> list = new ArrayList<>();
        coll(pos, laby);
        for(Object el : coll(pos, laby)){
            list.add(el);
        }
        System.out.println(list);
        return list;
    }

    private Set coll(Point pos, Map<Point, List<Point>> laby) {
        Set<Object> col = new HashSet<>();
        for (Map.Entry mapentry : laby.entrySet()) {
            col.add(mapentry.getValue());
        }
        return col;
    }*/

}