import { create } from "zustand";

const FAVORITOS_KEY = "favoritosPorUsuario";

function carregarFavoritos() {
  const data = localStorage.getItem(FAVORITOS_KEY);
  return data ? JSON.parse(data) : {};
}

function salvarFavoritos(favoritos: { [usuarioId: number]: number[] }) {
  localStorage.setItem(FAVORITOS_KEY, JSON.stringify(favoritos));
}

interface FavoritosStore {
  favoritosPorUsuario: { [usuarioId: number]: number[] };
  adicionarFavorito: (usuarioId: number, produtoId: number) => void;
  removerFavorito: (usuarioId: number, produtoId: number) => void;
  removerProdutoDeTodosFavoritos: (produtoId: number) => void;
  isFavorito: (usuarioId: number, produtoId: number) => boolean;
}

const useFavoritosStore = create<FavoritosStore>((set, get) => ({
  favoritosPorUsuario: carregarFavoritos(),
  adicionarFavorito: (usuarioId, produtoId) =>
    set((state) => {
      const favoritos = state.favoritosPorUsuario[usuarioId] || [];
      if (!favoritos.includes(produtoId)) {
        const novos = {
          ...state.favoritosPorUsuario,
          [usuarioId]: [...favoritos, produtoId],
        };
        salvarFavoritos(novos);
        return { favoritosPorUsuario: novos };
      }
      return state;
    }),
  removerFavorito: (usuarioId, produtoId) =>
    set((state) => {
      const favoritos = state.favoritosPorUsuario[usuarioId] || [];
      const novos = {
        ...state.favoritosPorUsuario,
        [usuarioId]: favoritos.filter((id) => id !== produtoId),
      };
      salvarFavoritos(novos);
      return { favoritosPorUsuario: novos };
    }),
  removerProdutoDeTodosFavoritos: (produtoId) =>
    set((state) => {
      const novos: { [usuarioId: number]: number[] } = {};
      for (const usuarioId in state.favoritosPorUsuario) {
        novos[usuarioId] = state.favoritosPorUsuario[usuarioId].filter((id) => id !== produtoId);
      }
      salvarFavoritos(novos);
      return { favoritosPorUsuario: novos };
    }),
  isFavorito: (usuarioId, produtoId) => {
    const favoritos = get().favoritosPorUsuario[usuarioId] || [];
    return favoritos.includes(produtoId);
  },
}));

export default useFavoritosStore;
