'use client';

import { useState, useEffect } from 'react';
import { useRouter } from 'next/navigation';
import { useAuth } from '@/contexts/AuthContext';
import { User } from '@/contexts/AuthContext';

export interface FeedBack {
  id: string;
  texto: string;
  denuncia: string;
}

export interface Denuncia {
  id: string;
  titulo: string;
  texto: string;
  urgencia: number;
  data: Date;
  userId: string;
  tipo: {
    id: string;
    nome: string;
  };
  usuario: User;
  feedBack: FeedBack;
}

export default function NewComplaint() {
  const router = useRouter();
  const { user } = useAuth();
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [tipo, setTipo] = useState('');
  const [urgencia, setUrgencia] = useState(1);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [tipos, setTipos] = useState<Array<{ id: string; nome: string }>>([]);
  const {user: userData} = useAuth();

  useEffect(() => {
    const fetchTipos = async () => {
      const response = await fetch('/api/tipo');
      const data = await response.json();
      setTipos(data);
    };
    fetchTipos();
  }, []);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsSubmitting(true);

    try {
      if (!userData) {
        throw new Error('User not authenticated');
      }

      const denuncia = {
        id: '',
        titulo: title,
        texto: description,
        urgencia: urgencia,
        data: new Date(),
        userId: userData.id.toString(),
        tipo: {
          id: tipo,
          nome: tipos.find(t => t.id === tipo)?.nome || ''
        },
        usuario: userData,
      };

      console.log(denuncia);

      const response = await fetch('/api/denuncia', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(denuncia),
      });

      if (!response.ok) {
        throw new Error('Failed to submit complaint');
      }

      router.push('/cidadao');
    } catch (error) {
      console.error('Error submitting complaint:', error);
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="space-y-6">
      <div className="md:grid md:grid-cols-3 md:gap-6">
        <div className="md:col-span-1">
          <h3 className="text-lg font-medium leading-6 text-gray-900">
            Nova Denúncia
          </h3>
          <p className="mt-1 text-sm text-gray-500">
            Preencha os dados da sua denúncia abaixo.
          </p>
        </div>
        <div className="mt-5 md:mt-0 md:col-span-2">
          <form onSubmit={handleSubmit}>
            <div className="shadow sm:rounded-md sm:overflow-hidden">
              <div className="px-4 py-5 bg-white space-y-6 sm:p-6">
                <div>
                  <label
                    htmlFor="title"
                    className="block text-sm font-medium text-gray-700"
                  >
                    Título
                  </label>
                  <input
                    type="text"
                    name="title"
                    id="title"
                    required
                    className="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md text-gray-700"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                  />
                </div>

                <div>
                  <label
                    htmlFor="description"
                    className="block text-sm font-medium text-gray-700"
                  >
                    Descrição
                  </label>
                  <textarea
                    id="description"
                    name="description"
                    rows={3}
                    required
                    className="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md text-gray-700"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                  />
                </div>

                <div>
                  <label
                    htmlFor="tipo"
                    className="block text-sm font-medium text-gray-700"
                  >
                    Tipo da Denúncia
                  </label>
                  <select
                    id="tipo"
                    name="tipo"
                    required
                    className="text-gray-700 mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                    value={tipo}
                    onChange={(e) => setTipo(e.target.value)}
                  >
                    <option value="">Selecione um tipo</option>
                    {tipos.map((tipo) => (
                      <option key={tipo.id} value={tipo.id}>
                        {tipo.nome}
                      </option>
                    ))}
                  </select>
                </div>

                <div>
                  <label
                    htmlFor="urgencia"
                    className="block text-sm font-medium text-gray-700"
                  >
                    Nível de Urgência
                  </label>
                  <select
                    id="urgencia"
                    name="urgencia"
                    required
                    className="text-gray-700 mt-1 block w-full py-2 px-3 border border-gray-300 bg-white rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                    value={urgencia}
                    onChange={(e) => setUrgencia(Number(e.target.value))}
                  >
                    <option value={1}>Baixa</option>
                    <option value={2}>Média</option>
                    <option value={3}>Alta</option>
                  </select>
                </div>
              </div>
              <div className="px-4 py-3 bg-gray-50 text-right sm:px-6">
                <button
                  type="submit"
                  disabled={isSubmitting}
                  className="inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                >
                  {isSubmitting ? 'Enviando...' : 'Enviar Denúncia'}
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
} 