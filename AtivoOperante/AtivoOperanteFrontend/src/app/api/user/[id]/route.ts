import { NextResponse } from 'next/server';

export async function GET(
  request: Request,
  context: { params: { id: string } }
) {
  try {
    const { id } = await Promise.resolve(context.params);
    const token = request.headers.get('cookie')?.split('; ')
      .find(row => row.startsWith('token='))?.split('=')[1];

    console.log('Fetching complaints for user:', id);
    console.log('Using token:', token);

    const response = await fetch(`http://localhost:8080/api/denuncia?userId=${id}`, {
      method: 'GET',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      }
    });
    
    if (!response.ok) {
      const errorData = await response.text();
      console.error('Backend error response:', errorData);
      return NextResponse.json(
        { message: 'Get complaints failed', details: errorData },
        { status: response.status }
      );
    }

    const data = await response.json();
    return NextResponse.json(data, { status: response.status });
    
  } catch (error) {
    console.error('User API (get complaints) error:', error);
    return NextResponse.json(
      { message: 'Internal server error', details: error instanceof Error ? error.message : 'Unknown error' },
      { status: 500 }
    );
  }
}