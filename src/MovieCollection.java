import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection
{
    private ArrayList<Movie> movies;
    private Scanner scanner;

    public MovieCollection(String fileName)
    {
        importMovieList(fileName);
        scanner = new Scanner(System.in);
    }

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }

    public void menu()
    {
        String menuOption = "";

        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");

        while (!menuOption.equals("q"))
        {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (!menuOption.equals("q"))
            {
                processOption(menuOption);
            }
        }
    }

    private void processOption(String option)
    {
        if (option.equals("t"))
        {
            searchTitles();
        }
        else if (option.equals("c"))
        {
            searchCast();
        }
        else if (option.equals("k"))
        {
            searchKeywords();
        }
        else if (option.equals("g"))
        {
            listGenres();
        }
        else if (option.equals("r"))
        {
            listHighestRated();
        }
        else if (option.equals("h"))
        {
            listHighestRevenue();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }

    private void searchTitles()
    {
        System.out.print("Enter a titles search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void sortResults(ArrayList<Movie> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void displayMovieInfo(Movie movie)
    {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }
    private void searchCast()
    {
        //search
        System.out.println("Enter a name");
        String search = scanner.nextLine();
        search = search.toLowerCase();
        ArrayList<String> Casts = new ArrayList<>();
        ArrayList<Movie> results = new ArrayList<>();
        for (Movie movie : movies) {
            String movieCast = movie.getCast();
            String[] castList = movieCast.split("\\|");
            for (String cast : castList) {
                if (cast.toLowerCase().contains(search)) {
                    if (!Casts.contains(cast)) {
                        Casts.add(cast);
                        }
                    }
                }
            }
            Casts.sort(String::compareTo);

            for (int i = 0; i < Casts.size(); i++){
                System.out.println(i+1+". "+Casts.get(i));
            }

            System.out.println("Who do you like to learn more about?");
            System.out.println("Enter the number");
            int search2 = scanner.nextInt();
            while (search2 > Casts.size() || search2 < 1){
                System.out.println("Enter the number (invalid choice)");
                search2 = scanner.nextInt();
                scanner.nextLine();
            }
            String actor = Casts.get(search2-1);

            for(int i = 0; i< movies.size();i++){
            Movie movie = movies.get(i);
                String[] movieCast = movie.getCast().split("\\|");
                for (String name: movieCast){
                    if (name.equalsIgnoreCase(actor)){
                        results.add(movie);
                    }
                }
            }
            sortResults(results);
            System.out.println(actor);

            for (int i = 0; i < results.size(); i++) {
                String title = results.get(i).getTitle();

                int choiceNum = i + 1;

                System.out.println("" + choiceNum + ". " + title);
            }

            System.out.println("Which movie would you like to learn more about?");
            System.out.print("Enter number: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            Movie selectedMovie = results.get(choice - 1);

            displayMovieInfo(selectedMovie);

            System.out.println("\n ** Press Enter to Return to Main Menu **");
            scanner.nextLine();

        }

    private void searchKeywords()
    {
        System.out.print("Enter a Keyword search term: ");
        String searchTerm = scanner.nextLine();

        searchTerm = searchTerm.toLowerCase();

        ArrayList<Movie> results = new ArrayList<Movie>();

        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getKeywords();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(searchTerm) != -1)
            {
                //add the Movie object to the results list
                results.add(movies.get(i));
            }
        }

        sortResults(results);

        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listGenres()
    {
        ArrayList<String> Genres = new ArrayList<>();
        ArrayList<Movie> results = new ArrayList<>();
        for (Movie movie : movies) {
            String movieGenre = movie.getGenres();
            String[] genreList = movieGenre.split("\\|");
            for (String genre : genreList) {
                    if (!Genres.contains(genre)) {
                        Genres.add(genre);
                    }
            }
        }
        Genres.sort(String::compareTo);

        for (int i = 0; i < Genres.size(); i++){
            System.out.println(i+1+". "+ Genres.get(i));
        }

        System.out.println("What genre do you want to learn more about?");
        System.out.println("Enter the number");
        int search = scanner.nextInt();
        while (search > Genres.size() || search < 1){
            System.out.println("Enter the number (invalid choice)");
            search = scanner.nextInt();
            scanner.nextLine();
        }
        String gen = Genres.get(search -1);

        for(int i = 0; i< movies.size();i++){
            Movie movie = movies.get(i);
            String[] movieGenre = movie.getGenres().split("\\|");
            for (String name: movieGenre){
                if (name.equalsIgnoreCase(gen)){
                    results.add(movie);
                }
            }
        }
        sortResults(results);
        System.out.println(gen);

        for (int i = 0; i < results.size(); i++) {
            String title = results.get(i).getTitle();

            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listHighestRated()
    {
        ArrayList<Movie> stupid = movies;
        ArrayList<Movie> HighestRated = new ArrayList<>();

        for(int i = 0; i<50; i++)
        {
            double highest = 0;
            int HighestIndex = 0;
            for(int j = i; j< stupid.size(); j++)
            {
                if(stupid.get(j).getUserRating() > highest)
                {
                    highest = stupid.get(j).getUserRating();
                    HighestIndex = j;
                }
            }
            HighestRated.add(stupid.remove(HighestIndex));

        }


        for (int i = 0; i < HighestRated.size(); i++){
            String title = HighestRated.get(i) + "";
            String using = title.substring(7,title.indexOf(","));
            System.out.println(i+1+". "+ using + ": " + HighestRated.get(i).getUserRating());
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = HighestRated.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listHighestRevenue()
    {
        {
            ArrayList<Movie> stupid = movies;
            ArrayList<Movie> HighestRevenue = new ArrayList<>();

            for(int i = 0; i<50; i++)
            {
                double highest = 0;
                int HighestIndex = 0;
                for(int j = i; j< stupid.size(); j++)
                {
                    if(stupid.get(j).getRevenue() > highest)
                    {
                        highest = stupid.get(j).getRevenue();
                        HighestIndex = j;
                    }
                }
                HighestRevenue.add(stupid.remove(HighestIndex));

            }


            for (int i = 0; i < HighestRevenue.size(); i++){
                String title = HighestRevenue.get(i) + "";
                System.out.println(i+1+". "+ title.substring(7,title.indexOf(",")));;
            }

            System.out.println("Which movie would you like to learn more about?");
            System.out.print("Enter number: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            Movie selectedMovie = HighestRevenue.get(choice - 1);

            displayMovieInfo(selectedMovie);

            System.out.println("\n ** Press Enter to Return to Main Menu **");
            scanner.nextLine();
        }
    }


    private void importMovieList(String fileName)
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            movies = new ArrayList<Movie>();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] movieFromCSV = line.split(",");

                String title = movieFromCSV[0];
                String cast = movieFromCSV[1];
                String director = movieFromCSV[2];
                String tagline = movieFromCSV[3];
                String keywords = movieFromCSV[4];
                String overview = movieFromCSV[5];
                int runtime = Integer.parseInt(movieFromCSV[6]);
                String genres = movieFromCSV[7];
                double userRating = Double.parseDouble(movieFromCSV[8]);
                int year = Integer.parseInt(movieFromCSV[9]);
                int revenue = Integer.parseInt(movieFromCSV[10]);

                Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
                movies.add(nextMovie);
            }
            bufferedReader.close();
        }
        catch(IOException exception)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }
    }
}