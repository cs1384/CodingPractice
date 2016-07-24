package Design;

/**
 * Created by Tin on 7/18/16.
 */
public class UrlShortener{
    char[] map;
    public UrlShortener(){
        map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    }
    public String encodeUrlID(long urlid){
        long mapSize = map.length; //62
        StringBuilder sb = new StringBuilder();
        while(urlid>0){
            sb.append(map[(int)(urlid%mapSize)]);
            urlid /= mapSize;
        }
        return sb.reverse().toString();
    }
    public long decodeShortenUrl(String url){
        long id = 0;
        for(char c : url.toCharArray()){
            id *= 62;
            if('a'<=c && c<='z') id += c-'a';
            else if('A'<=c && c<='Z') id += c-'A'+26;
            else if('0'<=c && c<='9') id += c-'0'+52;
            else return -1;
        }
        return id;
    }

    public static void main(String[] args) {
        UrlShortener urlShortener = new UrlShortener();
        System.out.println(urlShortener.encodeUrlID(125));
        System.out.println(urlShortener.decodeShortenUrl("cb"));
        System.out.println(urlShortener.decodeShortenUrl(urlShortener.encodeUrlID(125)));
    }
}
