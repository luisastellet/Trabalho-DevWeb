import { create } from "zustand";

interface UsuarioStore{
    usuarioLogado: number;
    nomeUsuario: string;
    setUsuarioLogado: (novoUsuarioLogado: number) => void;
    setNomeUsuario: (nome: string) => void;
}

const useUsuarioStore = create<UsuarioStore>((set) => ({
    usuarioLogado: 0,
    nomeUsuario: "",
    setUsuarioLogado: (novoUsuarioLogado: number) => set(() => ({usuarioLogado: novoUsuarioLogado})),
    setNomeUsuario: (nome: string) => set(() => ({nomeUsuario: nome})),
}))
export default useUsuarioStore;