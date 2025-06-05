const API_URL = process.env.NEXT_PUBLIC_API_URL || 'http://localhost:8080/api';

const getHeaders = () => {
  const token = document.cookie
    .split('; ')
    .find(row => row.startsWith('token='))
    ?.split('=')[1];

  return {
    'Content-Type': 'application/json',
    'Authorization': token ? `Bearer ${token}` : '',
  };
};

// Órgãos
export const getOrgaos = async () => {
  const response = await fetch(`${API_URL}/orgao`, {
    headers: getHeaders(),
  });
  if (!response.ok) throw new Error('Failed to fetch orgãos');
  return response.json();
};

export const createOrgao = async (nome: string) => {
  const response = await fetch(`${API_URL}/orgao`, {
    method: 'POST',
    headers: getHeaders(),
    body: JSON.stringify({ nome }),
  });
  if (!response.ok) throw new Error('Failed to create orgão');
  return response.json();
};

export const updateOrgao = async (id: number, nome: string) => {
  const response = await fetch(`${API_URL}/orgao`, {
    method: 'PUT',
    headers: getHeaders(),
    body: JSON.stringify({ id, nome }),
  });
  if (!response.ok) throw new Error('Failed to update orgão');
  return response.json();
};

export const deleteOrgao = async (id: number, nome: string): Promise<void> => {
  const response = await fetch(`${API_URL}/orgao`, {
    method: 'DELETE',
    headers: getHeaders(),
    body: JSON.stringify({ id, nome }),
  });

  if (!response.ok) {
    throw new Error('Failed to delete orgao');
  }
  // No need to parse JSON for 204 responses
};

// Tipos de Problemas
export const getTiposProblemas = async () => {
  const response = await fetch(`${API_URL}/tipo`, {
    headers: getHeaders(),
  });
  if (!response.ok) throw new Error('Failed to fetch tipos de problemas');
  return response.json();
};

export const createTipoProblema = async (nome: string) => {
  const response = await fetch(`${API_URL}/tipo`, {
    method: 'POST',
    headers: getHeaders(),
    body: JSON.stringify({ nome }),
  });
  if (!response.ok) throw new Error('Failed to create tipo de problema');
  return response.json();
};

export const updateTipoProblema = async (id: number, nome: string) => {
  const response = await fetch(`${API_URL}/tipo`, {
    method: 'PUT',
    headers: getHeaders(),
    body: JSON.stringify({ id, nome }),
  });
  if (!response.ok) throw new Error('Failed to update tipo de problema');
  return response.json();
};

export const deleteTipoProblema = async (id: number) => {
  const response = await fetch(`${API_URL}/tipo`, {
    method: 'DELETE',
    headers: getHeaders(),
    body: JSON.stringify({ id }),
  });
  if (!response.ok) throw new Error('Failed to delete tipo de problema');
  return response.json();
};

// Denúncias
export const getDenuncias = async () => {
  const response = await fetch(`${API_URL}/denuncia/all`, {
    headers: getHeaders(),
  });
  if (!response.ok) throw new Error('Failed to fetch denúncias');
  
  const text = await response.text(); // Get raw response text first
  try {
    return JSON.parse(text); // Try to parse it as JSON
  } catch (e) {
    console.error('Failed to parse denuncias response:', text);
    throw new Error('Invalid JSON response from server');
  }
};

export const deleteDenuncia = async (id: number) => {
  const response = await fetch(`${API_URL}/denuncia`, {
    method: 'DELETE',
    headers: getHeaders(),
    body: JSON.stringify({ id }),
  });
  if (!response.ok) throw new Error('Failed to delete denúncia');
  // No need to parse JSON for successful DELETE operations
};

export const addFeedback = async (denunciaId: number, texto: string) => {
  const response = await fetch(`${API_URL}/denuncia/add-feedback/${encodeURIComponent(texto)}`, {
    method: 'POST',
    headers: getHeaders(),
    body: JSON.stringify({ id: denunciaId }),
  });
  if (!response.ok) throw new Error('Failed to add feedback');
  return response.json();
}; 