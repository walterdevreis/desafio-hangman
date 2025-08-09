package br.com.dio.hangman;

import br.com.dio.hangman.exception.GameIsFinishedException;
import br.com.dio.hangman.exception.LetterAlreadyInputtedException;
import br.com.dio.hangman.model.HangmanChar;
import br.com.dio.hangman.model.HangmanGame;

import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String... args){
        var characters = Stream.of(args)
                .map(a -> a.toLowerCase().charAt(0))
                .map(HangmanChar::new).toList();
        System.out.println(characters);
        var hangmanGame = new HangmanGame(characters);
        System.out.println("Bem vindo ao jogo da forca, tente adivinhar a palavra, boa sorte");
        System.out.println(hangmanGame);
        var option = -1;
        while (true){
            System.out.println("Selecione uma das opções: ");
            System.out.println("1 - Informar uma letra");
            System.out.println("2 - Verificar status do jogo");
            System.out.println("3 - Sair do jogo");
            option = scanner.nextInt();
            switch (option){
                case 1 -> inputCharacter(hangmanGame);
                case 2 -> showGameStatus(hangmanGame);
                case 3 -> System.exit(0);
                default -> System.out.println("Opção inválida");
            }
        }

    }

    private static void showGameStatus(final HangmanGame hangmanGame){
        System.out.println(hangmanGame.getHangmanGameStatus());
        System.out.println(hangmanGame);
    }

    private static void inputCharacter(final HangmanGame hangmanGame){
        System.out.println("Informe uma letra");
        var character = scanner.next().charAt(0);
        try {
            hangmanGame.inputCharacter(character);
        }
        catch (LetterAlreadyInputtedException e){
            System.out.println(e.getMessage());
        }
        catch (GameIsFinishedException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
        System.out.println(hangmanGame);
    }
}
