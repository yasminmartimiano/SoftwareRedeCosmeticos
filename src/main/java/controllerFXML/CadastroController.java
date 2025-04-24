package controllerFXML;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CadastroController {

    @FXML
    private TextField nomeUsuarioField;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField cpfField;

    @FXML
    private TextField cnpjField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField telefoneField;

    @FXML
    private TextField dataNascimentoField;

    @FXML
    private TextField salarioField;

    @FXML
    private TextField enderecoField;

    @FXML
    private PasswordField senhaField;

    @FXML
    private ComboBox<String> cargoComboBox;

    @FXML
    private Button cadastroButton;

    @FXML
    public void initialize() {
        cargoComboBox.getItems().addAll(
                "Gerente",
                "Estoquista",
                "Vendedor",
                "Caixa"
        );
    }

    @FXML
    private void cadastrarUsuario() {
        String nome = nomeField.getText();
        String cpf = cpfField.getText();
        String email = emailField.getText();
        String telefone = telefoneField.getText();
        String cargo = cargoComboBox.getValue();
        // mensagem pra quando o cadastro for realizado
        System.out.println("Cadastro realizado com sucesso:");
        System.out.println("Nome: " + nome + " | Cargo: " + cargo);
    }
}
