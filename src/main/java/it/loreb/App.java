package it.loreb;

public class App 
{
    public static void main( String[] args )
    {
        AppClient client = new AppClient("localhost", 6789);
        client.comunicate();
    }
}
