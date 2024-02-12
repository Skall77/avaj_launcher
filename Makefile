all:
	find . -name "*.java" > sources.txt
	javac -sourcepath . @sources.txt
	java com.gmail.aaudevar.avajlauncher.Simulator scenario.txt


clean:
	find . -name '*.class' -delete
	rm -f sources.txt

fclean: clean
	rm -f simulation.txt

re: fclean all

.PHONY: all clean fclean re
