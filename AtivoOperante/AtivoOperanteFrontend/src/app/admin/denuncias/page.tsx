'use client';

import { useEffect, useState } from 'react';
import { getDenuncias, deleteDenuncia, addFeedback } from '../../api/admin';

interface Denuncia {
  id: number;
  titulo: string;
  texto: string;
  urgencia: number;
  data: string;
  usuario: {
    id: number;
    nome: string;
  } | null;
  tipo: {
    id: number;
    nome: string;
  } | null;
  feedBack?: {
    texto: string;
  } | null;
}

export default function ComplaintsPage() {
  const [denuncias, setDenuncias] = useState<Denuncia[]>([]);
  const [selectedDenuncia, setSelectedDenuncia] = useState<Denuncia | null>(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [feedback, setFeedback] = useState('');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    fetchDenuncias();
  }, []);

  const fetchDenuncias = async () => {
    try {
      setLoading(true);
      const data = await getDenuncias();
      setDenuncias(data);
      setError(null);
    } catch (err) {
      setError('Erro ao carregar denúncias');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id: number) => {
    if (!confirm('Tem certeza que deseja excluir esta denúncia?')) return;

    try {
      await deleteDenuncia(id);
      setDenuncias(denuncias.filter((denuncia) => denuncia.id !== id));
    } catch (err) {
      setError('Erro ao excluir denúncia');
      console.error(err);
    }
  };

  const handleOpenFeedbackModal = (denuncia: Denuncia) => {
    setSelectedDenuncia(denuncia);
    setFeedback(denuncia.feedBack?.texto || '');
    setIsModalOpen(true);
  };

  const handleSubmitFeedback = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!selectedDenuncia) return;

    try {
      await addFeedback(selectedDenuncia.id, feedback);
      setDenuncias(
        denuncias.map((denuncia) =>
          denuncia.id === selectedDenuncia.id
            ? { ...denuncia, feedBack: { texto: feedback } }
            : denuncia
        )
      );
      setIsModalOpen(false);
    } catch (err) {
      setError('Erro ao adicionar feedback');
      console.error(err);
    }
  };

  if (loading) {
    return (
      <div className="flex justify-center items-center h-64">
        <div className="text-lg text-gray-600">Carregando...</div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="flex justify-center items-center h-64">
        <div className="text-lg text-red-600">{error}</div>
      </div>
    );
  }

  return (
    <div className="space-y-6">
      <div className="sm:flex sm:items-center">
        <div className="sm:flex-auto">
          <h1 className="text-xl font-semibold text-gray-900">Denúncias</h1>
          <p className="mt-2 text-sm text-gray-700">
            Lista de todas as denúncias recebidas.
          </p>
        </div>
      </div>

      <div className="mt-8 flex flex-col">
        <div className="-my-2 -mx-4 overflow-x-auto sm:-mx-6 lg:-mx-8">
          <div className="inline-block min-w-full py-2 align-middle md:px-6 lg:px-8">
            <div className="overflow-hidden shadow ring-1 ring-black ring-opacity-5 md:rounded-lg">
              <table className="min-w-full divide-y divide-gray-300">
                <thead className="bg-gray-50">
                  <tr>
                    <th
                      scope="col"
                      className="py-3.5 pl-4 pr-3 text-left text-sm font-semibold text-gray-900 sm:pl-6"
                    >
                      Título
                    </th>
                    <th
                      scope="col"
                      className="px-3 py-3.5 text-left text-sm font-semibold text-gray-900"
                    >
                      Cidadão
                    </th>
                    <th
                      scope="col"
                      className="px-3 py-3.5 text-left text-sm font-semibold text-gray-900"
                    >
                      Tipo
                    </th>
                    <th
                      scope="col"
                      className="px-3 py-3.5 text-left text-sm font-semibold text-gray-900"
                    >
                      Data
                    </th>
                    <th
                      scope="col"
                      className="relative py-3.5 pl-3 pr-4 sm:pr-6"
                    >
                      <span className="sr-only">Ações</span>
                    </th>
                  </tr>
                </thead>
                <tbody className="divide-y divide-gray-200 bg-white">
                  {denuncias.map((denuncia) => (
                    <tr key={denuncia.id}>
                      <td className="whitespace-nowrap py-4 pl-4 pr-3 text-sm font-medium text-gray-900 sm:pl-6">
                        {denuncia.titulo}
                      </td>
                      <td className="whitespace-nowrap px-3 py-4 text-sm text-gray-500">
                        {denuncia.usuario?.nome || 'N/A'}
                      </td>
                      <td className="whitespace-nowrap px-3 py-4 text-sm text-gray-500">
                        {denuncia.tipo?.nome || 'N/A'}
                      </td>
                      <td className="whitespace-nowrap px-3 py-4 text-sm text-gray-500">
                        {new Date(denuncia.data).toLocaleDateString()}
                      </td>
                      <td className="relative whitespace-nowrap py-4 pl-3 pr-4 text-right text-sm font-medium sm:pr-6">
                        <button
                          onClick={() => handleOpenFeedbackModal(denuncia)}
                          className="text-indigo-600 hover:text-indigo-900 mr-4"
                        >
                          Feedback
                        </button>
                        <button
                          onClick={() => handleDelete(denuncia.id)}
                          className="text-red-600 hover:text-red-900"
                        >
                          Excluir
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>

      {isModalOpen && selectedDenuncia && (
        <div className="fixed z-10 inset-0 overflow-y-auto">
          <div className="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0">
            <div
              className="fixed inset-0 transition-opacity"
              aria-hidden="true"
            >
              <div className="absolute inset-0 bg-gray-500 opacity-75"></div>
            </div>

            <div className="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full">
              <form onSubmit={handleSubmitFeedback}>
                <div className="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
                  <div>
                    <label
                      htmlFor="feedback"
                      className="block text-sm font-medium text-gray-700"
                    >
                      Feedback
                    </label>
                    <textarea
                      id="feedback"
                      name="feedback"
                      rows={3}
                      className="mt-1 focus:ring-indigo-500 focus:border-indigo-500 block w-full shadow-sm sm:text-sm border-gray-300 rounded-md"
                      value={feedback}
                      onChange={(e) => setFeedback(e.target.value)}
                    />
                  </div>
                </div>
                <div className="bg-gray-50 px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse">
                  <button
                    type="submit"
                    className="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-indigo-600 text-base font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:ml-3 sm:w-auto sm:text-sm"
                  >
                    Salvar
                  </button>
                  <button
                    type="button"
                    onClick={() => setIsModalOpen(false)}
                    className="mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 sm:mt-0 sm:ml-3 sm:w-auto sm:text-sm"
                  >
                    Cancelar
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      )}
    </div>
  );
} 