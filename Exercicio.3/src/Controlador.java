import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Controlador {
	
public static void main(String[]args) {
	ArrayList<Tarefa> listaEncadeada = new ArrayList <>();
	char opcao;
	do {
		String menu = "Gerenciador de Tarefas: \n" +
					"a. Adicionar uma tarefa.\n" + 
					"b. Remover uma tarefa.\n" + 
					"c. Listar todas as tarefas.\n" +
					"d. Sair do programa.\n";
		String escolha = JOptionPane.showInputDialog(menu);
		
		if (escolha == null || escolha.isEmpty()) {
			opcao = 'd';
		}
		else {
			opcao = escolha.toLowerCase().charAt(0);
		}
		switch (opcao) {
		case 'a':
			String codigoStr = JOptionPane.showInputDialog("Digite o código da tarefa:");
				String titulo = JOptionPane.showInputDialog("Digite o título da tarefa:");
				try {
					int codigo=Integer.parseInt(codigoStr);
					
					listaEncadeada.add(new Tarefa(codigo, titulo));
					
					JOptionPane.showMessageDialog(null, "Tarefa adicionada!");
					
				}catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Código inválido. Use apenas números.");
				}
				break;
				
				case 'b':
				
				String codRemoverStr = JOptionPane.showInputDialog("Digite o código da tarefa a ser removida:");
					try {
					   int codRemover = Integer.parseInt(codRemoverStr);
					   boolean removida = false;
					   for (int i = 0; i < listaEncadeada.size(); i++) {
						  if (listaEncadeada.get(i).getCodigo()== codRemover) {
							  listaEncadeada.remove(i);
							  removida = true;
							  JOptionPane.showMessageDialog(null,  "Tarefa removida!");;
							  break;
						  	}
						  }
					   	  if (!removida) {
					   		  JOptionPane.showMessageDialog(null,  "Tarefa não encontrada.");
						  }
						} catch (NumberFormatException e) {
						   JOptionPane.showMessageDialog(null, "Codigo invalido. use apenas números.");
						}
						break;
						
						case 'c':
						  if (listaEncadeada.isEmpty()) {
							  JOptionPane.showMessageDialog(null,  "Nenhuma tarefa na lista.");
						  } else {
							  StringBuilder lista = new StringBuilder("Lista de tarefas:\n");
							  for (Tarefa t : listaEncadeada) {
								 lista.append(t.toString()).append("\n");
							  }
							  JOptionPane.showMessageDialog(null, lista.toString());
						  	}
						  	break;
						  	
						case 'd':
							JOptionPane.showMessageDialog(null,  "Saindo do programa. Tchau!");
							break;
							
							default:
								JOptionPane.showMessageDialog(null, "Opçaõ inválida. Tente novamente.");
		}
	  } while (opcao != 'd');  
						
	}
}

