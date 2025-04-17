document.addEventListener("DOMContentLoaded", function () {
  const loginBtn = document.querySelector("button[type='btn-login']");
  const emailInput = document.querySelector("input[type='email']");
  const passwordInput = document.querySelector("input[type='password']");

  if (loginBtn && emailInput && passwordInput) {
    loginBtn.addEventListener("click", function (e) {
      if (!emailInput.value || !passwordInput.value) {
        e.preventDefault();
        alert("Preencha todos os campos antes de fazer login.");
      }
    });
  }

  const navLinks = document.querySelectorAll(".rotas a, .sidebar a");
  const currentURL = window.location.href;

  navLinks.forEach((link) => {
    if (currentURL.includes(link.getAttribute("href"))) {
      link.style.fontWeight = "bold";
      link.style.color = "#9a9bff";
    }
  });

  const estoqueSection = document.querySelector(".section h2");
  if (estoqueSection && estoqueSection.textContent.includes("Estoque")) {
    estoqueSection.addEventListener("click", () => {
      alert("Você clicou em Estoque!");
    });
  }

  const containerText = document.querySelector(".container .text");
  if (containerText) {
    containerText.textContent = "Em breve você poderá agendar seus horários aqui!";
  }
});
