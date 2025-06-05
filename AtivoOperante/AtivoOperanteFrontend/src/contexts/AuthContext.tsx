'use client';

import { createContext, useContext, useState, ReactNode, useEffect } from 'react';

export interface User {
  id: string;
  nome: string;
  email: string;
  cpf: string;
  nivel: 1 | 2;
}

interface AuthContextType {
  user: User | null;
  login: (email: string, senha: string) => Promise<void>;
  register: (nome: string, email: string, senha: string, cpf: string) => Promise<void>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export function AuthProvider({ children }: { children: ReactNode }) {
  const [user, setUser] = useState<User | null>(null);
  const [isInitialized, setIsInitialized] = useState(false);

  useEffect(() => {
    const savedUser = localStorage.getItem('user');
    if (savedUser) {
      setUser(JSON.parse(savedUser));
    }
    setIsInitialized(true);
  }, []);

  const login = async (email: string, senha: string) => {
    const response = await fetch('/api/auth/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ email, senha }),
    });

    const data = await response.json();
    if (response.ok) {
      document.cookie = `token=${data.token}; path=/`;
      const userData = {
        id: data.usuario.id,
        nome: data.usuario.nome,
        email: data.usuario.email,
        cpf: data.usuario.cpf,
        nivel: data.usuario.nivel
      };
      setUser(userData);
      localStorage.setItem('user', JSON.stringify(userData));
    } else {
      throw new Error(data.message || 'Login failed');
    }
  };

  const register = async (nome: string, email: string, senha: string, cpf: string) => {
    const nivel = 1;
    const response = await fetch('/api/auth/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ nome, email, senha, cpf, nivel }),
    });

    const data = await response.json();
    if (response.ok) {
      const userData = {
        id: data.usuario.id,
        nome: data.usuario.nome,
        email: data.usuario.email,
        cpf: data.usuario.cpf,
        nivel: data.usuario.nivel
      };
      setUser(userData);
      localStorage.setItem('user', JSON.stringify(userData));
    } else {
      throw new Error(data.message || 'Registration failed');
    }
  };

  const logout = () => {
    setUser(null);
    localStorage.removeItem('user');
    document.cookie = 'token=; path=/; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
  };

  if (!isInitialized) {
    return null; // or a loading spinner
  }

  return (
    <AuthContext.Provider value={{ user, login, register, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
} 