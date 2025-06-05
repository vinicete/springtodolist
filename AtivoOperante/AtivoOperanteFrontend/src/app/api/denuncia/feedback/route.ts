import { NextResponse } from 'next/server';

export async function POST(
  request: Request
) {
  try {
    const token = request.headers.get('cookie')?.split('; ')
      .find(row => row.startsWith('token='))?.split('=')[1];

    const body = await request.json();

    const denuncia = {
      id: body.denuncia.id,
      titulo: body.denuncia.titulo,
      texto: body.denuncia.texto,
      urgencia: body.denuncia.urgencia,
      data: body.denuncia.data,
      tipo: { id: body.denuncia.tipo.id, nome: body.denuncia.tipo.nome },
    }

    const response = await fetch(`http://localhost:8080/api/denuncia/add-feedback/${body.texto}`, {
      method: 'POST',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(denuncia)
    });

    if (!response.ok) {
      const errorData = await response.text();
      console.error('Backend error response:', errorData);
      return NextResponse.json(
        { message: 'Failed to submit feedback', details: errorData },
        { status: response.status }
      );
    }

    return NextResponse.json({ message: 'Feedback submitted successfully' });
  } catch (error) {
    console.error('Error submitting feedback:', error);
    return NextResponse.json(
      { message: 'Failed to submit feedback', details: error instanceof Error ? error.message : 'Unknown error' },
      { status: 500 }
    );
  }
} 