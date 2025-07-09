import { useState } from "react";
import { z } from "zod";

const schema = z.object({
  conta: z.string().email({ message: "Email inválido" }),
  senha: z.string().min(6, { message: "A senha deve ter pelo menos 6 caracteres" }),
  confirmacaoSenha: z.string(),
}).refine((data) => data.senha === data.confirmacaoSenha, {
  message: "As senhas não coincidem",
  path: ["confirmacaoSenha"],
});

interface Props {
  onSuccess: () => void;
}

const CadastroUsuarioForm = ({ onSuccess }: Props) => {
  const [form, setForm] = useState({ conta: "", senha: "", confirmacaoSenha: "" });
  const [errors, setErrors] = useState<{ [key: string]: string }>({});
  const [apiError, setApiError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
    setErrors({ ...errors, [e.target.name]: "" });
    setApiError("");
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setApiError("");
    const result = schema.safeParse(form);
    if (!result.success) {
      const fieldErrors: { [key: string]: string } = {};
      result.error.errors.forEach((err) => {
        if (err.path[0]) fieldErrors[err.path[0]] = err.message;
      });
      setErrors(fieldErrors);
      return;
    }
    setLoading(true);
    try {
      const res = await fetch("http://localhost:8080/cadastro", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ conta: form.conta, senha: form.senha }),
      });
      if (!res.ok) {
        const data = await res.json();
        setApiError(data.message || "Erro ao cadastrar usuário");
        if (data.message && data.message.toLowerCase().includes("já cadastrada")) {
          setTimeout(() => window.location.href = "/login", 1500);
        }
      } else {
        onSuccess();
      }
    } catch (err) {
      setApiError("Erro de conexão com o servidor");
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={handleSubmit} style={{ maxWidth: 400, margin: "auto" }}>
      <h2>Cadastro de Usuário</h2>
      <div className="mb-3">
        <label className="form-label">Email</label>
        <input
          type="email"
          name="conta"
          className={`form-control ${errors.conta ? "is-invalid" : ""}`}
          value={form.conta}
          onChange={handleChange}
        />
        {errors.conta && <div className="invalid-feedback">{errors.conta}</div>}
      </div>
      <div className="mb-3">
        <label className="form-label">Senha</label>
        <input
          type="password"
          name="senha"
          className={`form-control ${errors.senha ? "is-invalid" : ""}`}
          value={form.senha}
          onChange={handleChange}
        />
        {errors.senha && <div className="invalid-feedback">{errors.senha}</div>}
      </div>
      <div className="mb-3">
        <label className="form-label">Confirmação de Senha</label>
        <input
          type="password"
          name="confirmacaoSenha"
          className={`form-control ${errors.confirmacaoSenha ? "is-invalid" : ""}`}
          value={form.confirmacaoSenha}
          onChange={handleChange}
        />
        {errors.confirmacaoSenha && <div className="invalid-feedback">{errors.confirmacaoSenha}</div>}
      </div>
      {apiError && <div className="alert alert-danger">{apiError}</div>}
      <button type="submit" className="btn btn-primary w-100" disabled={loading}>
        {loading ? "Cadastrando..." : "Cadastrar"}
      </button>
      <div className="mt-3 text-center">
        <span>Já possui uma conta?{' '}</span>
        <button
          type="button"
          className="btn btn-link p-0"
          style={{ color: '#8B4513', textDecoration: "none", fontWeight: 500 }}
          onClick={() => window.location.href = "/login"}
        >
          Entre
        </button>
      </div>
    </form>
  );
};

export default CadastroUsuarioForm;
