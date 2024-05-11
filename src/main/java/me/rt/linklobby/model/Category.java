package me.rt.linklobby.model;

import me.rt.linklobby.LinkLobby;
import me.rt.linklobby.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Category {
    private static final List<String> CATEGORIES = new ArrayList<>();
    private static final File file = new File(LinkLobby.getInstance().getDataFolder().getAbsolutePath() + "/linklCategories.txt");
    private Category() { }

    public static List<String> CATEGORIES() {
        return CATEGORIES;
    }

    public static void initializeCategory() {
        if (!file.exists()) {
            try {
                file.getParentFile().mkdir();
                file.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                String[] data = {"NAME", "AGE", "DISCORD", "STATUS", "GENDER", "PRONOUNS"};
                for (String s : data) {
                    writer.write(s);
                    writer.newLine();
                }
                writer.flush();
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                CATEGORIES.add(line.trim().toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static String stringify() {
        StringBuilder sb = new StringBuilder();
        List<String> capitalizedStrings = CATEGORIES.stream()
                .map(Utils::capitalizeWords)
                .collect(Collectors.toList());

        for (int i = 0; i < capitalizedStrings.size()-1; i++) {
            sb.append(capitalizedStrings.get(i)).append(", ");
        }

        sb.append(capitalizedStrings.get(capitalizedStrings.size()-1));

        return sb.toString();
    }

}
