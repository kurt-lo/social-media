import { create } from "zustand";

const useAuthStore = create((set) => {
    const token = localStorage.getItem("authUserToken") || null;
    const role = localStorage.getItem("authUserRole") || null;
    const username = localStorage.getItem("authUserName") || null;
    return {
        user: token ? { token, role, username} : null,

        login: (userData, token) => {
            set({ user: { ...userData, token } });
            localStorage.setItem("authUserToken", token);
            localStorage.setItem("authUserRole", userData.role);
            localStorage.setItem("authUserName", userData.username);
        },

        logout: () => {
            set({ user: null });
            localStorage.removeItem("authUserToken");
            localStorage.removeItem("authUserRole");
            localStorage.removeItem("authUserName");
        },

        isAuthenticated: () => !!useAuthStore.getState().user,
    }
})

export default useAuthStore;