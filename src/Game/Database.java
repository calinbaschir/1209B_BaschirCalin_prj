package Game;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    boolean verificareCreare = false;
    boolean verificareConexiune = false;
    private static Database instance = null;
    public static Database getInstance() {
        if(instance == null) {
            instance = new Database();
        }
        return instance;
    }


    public static void setScore(int score) {
        SQLiteDataSource dataBase = null;
        try {
            dataBase = new SQLiteDataSource();
            dataBase.setUrl("jdbc:sqlite:Database.db");
        } catch (Exception e) {
            System.out.println("Eroare la crearea bazei de date:");
            e.printStackTrace();
            System.exit(0);
        } finally {
            if(!getInstance().verificareCreare) {
                System.out.println("Baza de date a fost creata cu succes!");
                getInstance().verificareCreare = true;
            }
        }

        String query = "SELECT * FROM score";
        try(Connection connection = dataBase.getConnection();
            Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);
            PreparedStatement preparedStatement = connection.prepareStatement("update score set MaxScore = ?");
            while(resultSet.next()) {
                preparedStatement.setInt(1, score);
                preparedStatement.executeUpdate();
            }
        } catch(Exception e) {
            System.out.println("Eroare la conectarea cu baza de date:");
            e.printStackTrace();
            System.exit(0);
        } finally {
            if(!getInstance().verificareConexiune) {
                System.out.println("Conexiunea cu baza de date creata cu succes!");
                getInstance().verificareCreare = true;
            }

        }
    }

    public int getScore() {
        SQLiteDataSource dataBase = null;
        try {
            dataBase = new SQLiteDataSource();
            dataBase.setUrl("jdbc:sqlite:Database.db");
        } catch (Exception e) {
            System.out.println("Eroare la accesarea bazei de date:");
            e.printStackTrace();
            System.exit(0);
        } finally {
            if(!getInstance().verificareCreare) {
                System.out.println("Baza de date a fost accesata cu succes!");
                getInstance().verificareCreare = true;
            }
        }
        int maxScore = Integer.MAX_VALUE;
        String query = "SELECT * FROM score";
        try {
            Connection connection = dataBase.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                maxScore = resultSet.getInt("MaxScore");
            }
        } catch(Exception e) {
            System.out.println("Eroare la conectarea cu baza de date:");
            e.printStackTrace();
            System.exit(0);
        } finally {
            if(!getInstance().verificareConexiune) {
                System.out.println("Conexiunea cu baza de date creata cu succes!");
                getInstance().verificareConexiune = true;
            }
        }
        return maxScore;
    }

}
