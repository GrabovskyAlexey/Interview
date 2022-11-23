package ru.grabovsky.interview.database;

import ru.grabovsky.interview.database.models.Film;
import ru.grabovsky.interview.database.models.Session;

import java.sql.*;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DbHandler {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    public static void conn() throws ClassNotFoundException, SQLException {
        conn = null;
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection("jdbc:h2:./film.db;MODE=PostgreSQL", "sa", "");
        statmt = conn.createStatement();
        System.out.println("База Подключена!");
    }

    // --------Создание таблиц--------
    public static void createDB() throws SQLException {
        statmt.execute("CREATE TABLE IF NOT EXISTS films (\n" +
                "id BIGSERIAL NOT NULL UNIQUE,\n" +
                "name VARCHAR(255) NOT NULL,\n" +
                "duration INTEGER NOT NULL,\n" +
                "PRIMARY KEY(id));");
        statmt.execute("CREATE TABLE IF NOT EXISTS sessions (\n" +
                "id BIGSERIAL NOT NULL UNIQUE,\n" +
                "film_id INTEGER NOT NULL,\n" +
                "start_time TIME NOT NULL,\n" +
                "cost INTEGER NOT NULL,\n" +
                "FOREIGN KEY(film_id) REFERENCES films(id),\n" +
                "PRIMARY KEY(id));");
        statmt.execute("CREATE TABLE IF NOT EXISTS tickets (\n" +
                "id BIGSERIAL NOT NULL,\n" +
                "session_id INTEGER NOT NULL,\n" +
                "PRIMARY KEY(id),\n" +
                "FOREIGN KEY(session_id) REFERENCES sessions(id));");

        System.out.println("Таблицы создана или уже существует.");
    }

    // --------Заполнение таблицы--------
    public static void writeDB() throws SQLException {
        statmt.execute("INSERT INTO films (name, duration) VALUES \n" +
                "('Star Wars Episode IV', 60)," +     // 1
                "('Pulp Fiction', 90)," +             // 2
                "('Terminator', 90)," +               // 3
                "('Shawshank redemption', 120)," +    // 4
                "('Green mile', 120); ");             // 5

        LocalTime time = LocalTime.of(9, 0);

        statmt.execute("INSERT INTO sessions (film_id, start_time, cost) VALUES " +
                "(1, '" + time + "', 5)," +                                                                // 1
                "(3, '" + time.plusHours(1).plusMinutes(5) + "', 8)," +           // 2
                "(5, '" + time.plusHours(2).plusMinutes(25) + "', 6)," +          // 3
                "(1, '" + time.plusHours(4).plusMinutes(40) + "', 7)," +          // 4
                "(3, '" + time.plusHours(5).plusMinutes(50) + "', 12)," +         // 5
                "(2, '" + time.plusHours(8).plusMinutes(30) + "', 10)," +         // 6
                "(4, '" + time.plusHours(10).plusMinutes(40) + "', 15)," +        // 7
                "(5, '" + time.plusHours(14).plusMinutes(10) + "', 13);");        // 8

        statmt.execute("INSERT INTO tickets (session_id) VALUES " +
                "(1)," +
                "(3)," +
                "(1)," +
                "(1)," +
                "(2)," +
                "(4)," +
                "(5)," +
                "(6)," +
                "(7)," +
                "(8)," +
                "(6)," +
                "(2)," +
                "(4)," +
                "(7)," +
                "(3)," +
                "(8)," +
                "(4)," +
                "(1)," +
                "(3)," +
                "(5)," +
                "(8)," +
                "(2)," +
                "(7)," +
                "(6)," +
                "(2)," +
                "(4)," +
                "(2)," +
                "(8)," +
                "(7)," +
                "(6)," +
                "(5)," +
                "(3)," +
                "(4)," +
                "(7)," +
                "(8)," +
                "(5);");

        System.out.println("Таблица заполнена");
    }

    // -------- Вывод таблицы--------
    public static void readDB() throws SQLException {
        final List<Session> sessionData = getSessionData();
        if(sessionData.size() < 2){
            return;
        }
        printErrorSchedule(sessionData);
        printLongBreak(sessionData);
        printFilmStatistic(sessionData);

    }

    private static void printErrorSchedule(final List<Session> sessionData){
        System.out.println("Schedule error");
        for (int i = 1; i < sessionData.size(); i++) {
            Session current = sessionData.get(i);
            Session previous = sessionData.get(i - 1);
            boolean errorSchedule = current.getStartTime()
                    .isBefore(
                            previous.getStartTime().plusMinutes(previous.getFilm().getDuration()));
            if(errorSchedule){
                System.out.println(previous.getFilm().getName() + " | " + previous.getStartTime() + " | " + previous.getFilm().getDuration() + " | " +
                        current.getFilm().getName() + " | " + current.getStartTime() + " | " + current.getFilm().getDuration()
                );
            }
        }
    }

    private static void printLongBreak(final List<Session> sessionData){
        System.out.println("Long break");
        Map<Integer, List<Session>> breaks = new HashMap<>();
        for (int i = 1; i < sessionData.size(); i++) {
            Session current = sessionData.get(i);
            Session previous = sessionData.get(i - 1);
            boolean longBreak = current.getStartTime()
                    .isAfter(
                            previous.getStartTime().plusMinutes(previous.getFilm().getDuration() + 30));
            if(longBreak){
                final int between = (int) -ChronoUnit.MINUTES.between(current.getStartTime(), previous.getStartTime().plusMinutes(previous.getFilm().getDuration()));
                breaks.put(between, List.of(previous, current));
            }
        }
        final List<Integer> collect = breaks.keySet().stream()
                .sorted((o1, o2) -> o2 - o1)
                .collect(Collectors.toList());
        for(Integer i : collect){
            final List<Session> sessions = breaks.get(i);
            Session previous = sessions.get(0);
            Session current = sessions.get(1);
            System.out.println(previous.getFilm().getName() + " | " + previous.getStartTime() + " | " + previous.getFilm().getDuration() + " | " +
                    current.getStartTime() + " | " + i);
        }
    }

    private static void printFilmStatistic(final List<Session> sessionData){
        System.out.println("Film statistic");
        final Map<Film, List<Session>> collect = sessionData.stream().collect(Collectors.groupingBy(Session::getFilm, Collectors.toList()));
        for(Film f : collect.keySet()){
            int allCount = collect.get(f).stream()
                    .mapToInt(Session::getTicketCount)
                    .sum();
            double avgCount = collect.get(f).stream()
                    .mapToInt(Session::getTicketCount)
                    .average().getAsDouble();
            int sum = collect.get(f).stream()
                    .mapToInt(i -> i.getCost() * i.getTicketCount())
                    .sum();
            System.out.println(f.getName() + " | " + allCount  + " | " + avgCount  + " | " + sum );
        }
        int allCount = sessionData.stream()
                .mapToInt(Session::getTicketCount)
                .sum();
        double avgCount = sessionData.stream()
                .mapToInt(Session::getTicketCount)
                .average().getAsDouble();
        int sum = sessionData.stream()
                .mapToInt(i -> i.getCost() * i.getTicketCount())
                .sum();
        System.out.println("Итого | " + allCount  + " | " + avgCount  + " | " + sum );
    }

    private static List<Session> getSessionData() throws SQLException {
        String query = "select COUNT(t.ID), t.SESSION_ID, s.FILM_ID, s.START_TIME, s.COST, f.NAME, f.DURATION FROM TICKETS t\n" +
                "join SESSIONS s ON t.SESSION_ID = s.ID \n" +
                "JOIN FILMS f on s.FILM_ID = f.ID\n" +
                "group by t.SESSION_ID ORDER BY s.START_TIME";

        List<Session> sessions = new ArrayList<>();

        resSet = statmt.executeQuery(query);
        while(resSet.next()) {
            sessions.add(
                Session.builder()
                    .id(resSet.getLong(2))
                    .startTime(resSet.getObject(4, LocalTime.class))
                    .cost(resSet.getInt(5))
                    .film(
                            Film.builder()
                                    .id(resSet.getLong(3))
                                    .name(resSet.getString(6))
                                    .duration(resSet.getInt(7))
                                    .build()
                    )
                    .ticketCount(resSet.getInt(1))
                    .build()
            );

        }
        return sessions;
    }

    // --------Закрытие--------
    public static void closeDB() throws SQLException {
        conn.close();
        System.out.println("Соединения закрыты");
    }

}
